from itertools import chain


FORWARD = (1, 0)
FORWARD_LEFT = (1, -1)
FORWARD_RIGHT = (1, 1)
LEFT = (0, -1)
RIGHT = (0, 1)
BACKWARD = (-1, 0)
BACKWARD_LEFT = (-1, -1)
BACKWARD_RIGHT = (-1, 1)

ORTHOGONAL = (LEFT, RIGHT, FORWARD, BACKWARD)
DIAGONAL = (FORWARD_LEFT, FORWARD_RIGHT, BACKWARD_LEFT, BACKWARD_RIGHT)

class Side(object):

    def __init__(self, name):
        self.name = name
        self.abbreviation = name.upper()[0]

WHITE = Side("white")
BLACK = Side("black")

class Game(object):

    def __init__(self):
        self.board = Board()

    def setup(self):
        layout = [
            (Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook),
            (Pawn, Pawn, Pawn, Pawn, Pawn, Pawn, Pawn, Pawn)
        ]
        for column in range(8):
            for row in range(2):
                piece = layout[row][column]
                self.board.place(piece(BLACK), (7-row, column)) 
                self.board.place(piece(WHITE), (row, column)) 

# TODO handle "pat" (back, forth, back, forth)
# TODO handle chess
# TODO handle chess mate

class Board(object):

    def __init__(self):
        self.fields = {
            (row, column): None 
            for row in range(0, 8)
            for column in range(0, 8)
        }

    def place(self, piece, position):
        self.validate_position(position)
        if self.is_on_board(piece):
            raise ValueError(
                "The piece is already on the board")
        if self.get_piece_at(position):
            raise ValueError(
                "(%d, %d): another piece is on that field" % position)
        self.fields[position] = piece
        piece.set_board(self)
        return piece

    def validate_position(self, position):
        if not Board.is_valid_position(position):
            raise ValueError("(%d, %d): invalid board position" % position)

    @staticmethod
    def is_valid_position(position):
        row, column = position
        return 0 <= row <= 7 and 0 <= column <= 7

    def get_piece_at(self, position):
        self.validate_position(position)
        return self.fields[position]

    def is_empty_field(self, position):
        return not self.get_piece_at(position)

    @property
    def pieces(self):
        """Returns a dictionary, containing all the pieces that are currently on
        the board. Keys are positions, values are the pieces.
        """
        return {
            position: self.fields[position]
            for position in [
                (row, column)
                for row in range(0, 8)
                for column in range(0, 8)
            ]
            if self.fields[position]
        }

    def get_position_for(self, piece):
        try:
            return [
                position for position, piece_on_board in self.fields.items()
                if piece_on_board == piece
            ][0]
        except IndexError:
            raise ValueError("The piece is not on the board")

    def is_on_board(self, piece):
        return piece.board == self

class Piece(object):

    def __init__(self, side):
        self.side = side
        self.board = None
        self.nav = BoardNavigator(self)

    def set_board(self, board):
        self.board = board

    def assert_on_board(self):
        if not self.board:
            raise ValueError("The piece is not on a board")

    @property
    def position(self):
        self.assert_on_board()
        return self.board.get_position_for(self)

    def get_valid_moves(self):
        return dict(self.generate_valid_moves())

    def generate_valid_moves(self):
        self.assert_on_board()
        for direction in self.directions:
            self.nav.from_position()
            for _ in range(self.max_steps):
                if not self.nav.go(1, direction):
                   break
                target_piece = self.board.get_piece_at(self.nav.position)
                if target_piece:
                    if target_piece.side != self.side: 
                        yield (self.nav.position, target_piece)
                    break
                else:
                    yield (self.nav.position, None) 

class Queen(Piece):

    directions = ORTHOGONAL + DIAGONAL
    max_steps = 7

class King(Piece):

    directions = ORTHOGONAL + DIAGONAL
    max_steps = 1
    # TODO check if target position is under attack
    # TODO handle rook move

class Knight(Piece):

    directions = (
        (-1, -2), (-2, -1), (-2, 1), (-1, 2),
        ( 1, -2), ( 2, -1), ( 2, 1), ( 1, 2)
    )
    max_steps = 1

class Bishop(Piece):

    directions = DIAGONAL
    max_steps = 7

class Rook(Piece):
    directions = ORTHOGONAL
    max_steps = 7

class Pawn(Piece):

    def get_valid_moves(self):
        self.assert_on_board()
        return dict(chain(
            self._possible_moves_forward(),
            self._possible_captures(),
            self._possible_en_passant_captures()
        ))

    def _possible_moves_forward(self):
        if not self.nav.from_position().go(1, FORWARD) or \
           not self.nav.is_at_empty_field:
            return
        yield (self.nav.position, None)

        if self.is_at_start_position and \
           self.nav.from_position().go(2, FORWARD) and \
           self.nav.is_at_empty_field:
            yield(self.nav.position, None)

    def _possible_captures(self):
        for direction in (FORWARD_LEFT, FORWARD_RIGHT):
            if self.nav.from_position().go(1, direction):
                target_piece = self.board.get_piece_at(self.nav.position)
                if target_piece and target_piece.side != self.side:
                    yield(self.nav.position, target_piece)

    def _possible_en_passant_captures(self):
        for direction in (FORWARD_LEFT, FORWARD_RIGHT):
            if not self.nav.from_position().go(1, direction) or \
               not self.nav.is_at_empty_field:
                continue
            target_position = self.nav.position
            self.nav.go(1, BACKWARD)
            target_piece = self.board.get_piece_at(self.nav.position)
            if target_piece and \
               target_piece.side != self.side and \
               type(target_piece) == Pawn:
               # TODO and pawn moved two steps in previous turn 
                yield (target_position, target_piece)

    @property
    def is_at_start_position(self):
        start_row = 1 if self.side == WHITE else 6
        row, column = self.position
        return row == start_row
        # TODO also check if the piece has moved, or implement a validation
        # that lets the Pawn complain being at the first row.

    # TODO promotion

class BoardNavigator(object):
    """Is used to determine at what position a piece would end up on the
    board, when steps in a given direction would be taken.
    """

    def __init__(self, piece):
        self.piece = piece

    def from_position(self):
        self.position = self.piece.position
        return self

    def go(self, number_of_steps, direction):
        """Move the navigator a number of steps into a direction.
        Returns false when the navigator walks off the board.
        Returns true and sets the 'position' property to the new location
        when the navigator stays within the board.
        """
        transformation = self._get_transformation(direction)
        new_position = (
            self.position[0] + transformation[0] * number_of_steps,
            self.position[1] + transformation[1] * number_of_steps
        )
        if Board.is_valid_position(new_position):
            self.position = new_position
            return True
        return False 

    def _get_transformation(self, direction):
        """Translates the provided direction (one of the constants FORWARD,
        FORWARD_LEFT, RIGHT, etc.) into a move transformation for the piece's
        side. For example FORWARD for the white side provides transformation
        (+1, 0) and for the black side (-1, 0).
        """
        if self.piece.side == WHITE:
            return direction
        else:
            return (direction[0]*-1, direction[1]*-1)

    @property
    def is_at_empty_field(self):
        return self.piece.board.is_empty_field(self.position)


class Move(object):

    def __init__(self, piece):
        piece.assert_on_board()
        self.piece = piece
        self.valid_moves = piece.get_valid_moves()

    def is_allowed(self, position):
        return position in self.valid_moves

    def is_capture(self):
        return self.is_allowed(position) and self.valid_moves[position]

    def can_capture(self, target_piece):
        return target_piece in self.valid_moves.values()

    # TODO Extend to the point where this code works: Move(piece).to((5,4))
    # TODO Store move in a history, as prev move is needed by 
    #      the en passant pawn capturing

class AsciiPresentation(object):

    def __init__(self, board, for_side = WHITE):
        self.board = board
        self.for_side = for_side

    def render(self):
        fields = [["  "] * 8 for _ in range(8)]
        for (row, column), piece in self.board.pieces.items():
            fields[row][column] = self._render_piece(piece)
        return "\n".join(self._render_lines(fields))

    def _render_lines(self, fields):
        print fields
        horizontal_line = "+--" * 8 + "+"
        for row in self._order_for_side(fields):
            yield horizontal_line
            row = "|" + "|".join(self._order_for_side(row)) + "|"
            yield row
        yield horizontal_line

    def _order_for_side(self, data):
        return data if self.for_side == BLACK else reversed(data)

    def _render_piece(self, piece):
        if type(piece) is Knight:
            piece_name = "H"
        else:
            piece_name = type(piece).__name__[0]
        return piece.side.abbreviation + piece_name

        

# ---------- Functions as required by the exercise ------------

def board(white, black):
    board = Board()
    board.place(Queen(WHITE), white)
    board.place(Queen(BLACK), black)

    fields = [["0"] * 8 for _ in range(8)]
    for (row, column), piece in board.pieces.items():
        fields[row][column] = piece.side.abbreviation
    return ["".join(row) for row in fields]

def can_attack(white, black):
    board = Board()
    white = board.place(Queen(WHITE), white)
    black = board.place(Queen(BLACK), black)
    return Move(white).can_capture(black)

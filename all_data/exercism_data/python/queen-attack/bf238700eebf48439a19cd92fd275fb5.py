from itertools import product


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

    def get_transformation(self, direction):
        """Translates the provided direction (one of the constants FORWARD,
        FORWARD_LEFT, RIGHT, etc.) into a move transformation for the side.
        For example FORWARD for the white side provides transformation (-1, 0)
        and for the black side (+1, 0).
        """
        if self.name == "white":
            return direction
        else:
            return (direction[0]*-1, direction[1]*-1)

WHITE = Side("white")
BLACK = Side("black")

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
        column, row = position
        return 0 <= column <= 7 and 0 <= row <= 7

    def get_piece_at(self, position):
        self.validate_position(position)
        return self.fields[position]

    @property
    def pieces(self):
        """Returns a dictionary, containing all the pieces that are currently on
        the board. Keys are positions, values are the pieces.
        """
        return {
            position: self.fields[position]
            for position in [
                (column, row)
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

    def set_board(self, board):
        self.board = board

    def assert_on_board(self):
        if not self.board:
            raise ValueError("The piece is not on a board")

    @property
    def position(self):
        self.assert_on_board()
        return self.board.get_position_for(self)

class Queen(Piece):

    directions = ORTHOGONAL + DIAGONAL
    max_steps = 7

class King(Piece):

    directions = ORTHOGONAL + DIAGONAL
    max_steps = 1
    # TODO check if target position is under attack
    # TODO handle rook move
    # TODO handle repeated move prevention (back, forth, back, forth)
    # TODO check mate detection

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
        valid_moves = {}
        walker = BoardWalker(self)

        one_forward = walker.step(1, FORWARD)
        valid_moves[one_forward] = None

        two_forward = walker.step(2, FORWARD)
        valid_moves[two_forward] = None

        return valid_moves

        # TODO move check for piece at position
        # TODO en passant capture
        # TODO direct diagonal capture 
        # TODO promotion
        # TODO only allow two steps for first move

class ValidMoves(object):

    def __init__(self, piece):
        piece.assert_on_board();
        self.piece = piece

    def get(self):
        try:
            return self.piece.get_valid_moves()
        except AttributeError:
            return {
                position: piece_at_position
                for direction in self.piece.directions
                for position, piece_at_position in self._steps(direction)
            }

    def _steps(self, direction):
        """Generates possible steps from the current position into the provided
        direction (until the maximum number of steps, the edge of the board
        or another piece is encountered).
        When another piece is encountered, that piece's position is counted as
        a valid target position when it is a piece from the opposite side.
        """
        walker = BoardWalker(self.piece)
        for position in walker.walk(self.piece.max_steps, direction):
            piece_at_position = self.piece.board.get_piece_at(position)
            if piece_at_position:
                if piece_at_position.side != self.piece.side: 
                    yield (position, piece_at_position)
                break
            else:
                yield (position, None) 

class BoardWalker(object):

    def __init__(self, piece):
        self.piece = piece

    def walk(self, max_steps, direction):
        position = self.piece.position
        for _ in range(1, 8)[0:max_steps]:
            position = self._step(position, direction)
            if not position:
                break
            yield position

    def step(self, number_of_steps, direction):
        position = self.piece.position
        for _ in range(number_of_steps):
            position = self._step(position, direction)
            if not position:
                return None
        return position

    def _step(self, position, direction):
        row, column = position
        trans_row, trans_column = self.piece.side.get_transformation(direction)
        position = (row+trans_row, column+trans_column)
        if not Board.is_valid_position(position):
            return None
        return position

class Move(object):

    def __init__(self, piece):
        piece.assert_on_board()
        self.piece = piece
        self.valid_moves = ValidMoves(piece).get()

    def is_allowed(self, position):
        return position in self.valid_moves

    def is_capture(self):
        return self.is_allowed(position) and self.valid_moves[position]

    def can_capture(self, target_piece):
        return target_piece in self.valid_moves.values()

    # TODO Extend to the point where this code works: Move(piece).to((5,4))
    # TODO Store move in a history, as prev move is needed by 
    #      the en passant pawn capturing

# ---------- Functions as required by the exercise ------------

def board(white, black):
    board = Board()
    board.place(Queen(WHITE), white)
    board.place(Queen(BLACK), black)

    fields = [["0"] * 8 for _ in range(8)]
    for (column, row), piece in board.pieces.items():
        fields[column][row] = piece.side.abbreviation
    return ["".join(row) for row in fields]

def can_attack(white, black):
    board = Board()
    white = board.place(Queen(WHITE), white_position)
    black = board.place(Queen(BLACK), black_position)
    return Move(white).can_capture(black)

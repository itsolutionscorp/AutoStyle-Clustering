import re
from random import sample 


def board(layout):
    layout = TestLayout(layout)
    board = BoardFactory.create(layout)
    return TestLayout.rows_from_board(board)


class BoardFactory(object):

    @staticmethod
    def create(layout):
        """Creates a minesweeper Board object, using the layout as defined by 
        the provided layout object. The layout object must contain at least
        the following properties:
        - width and height: to define the size of the board.
        - bomb_positions: a list of (x,y) coordinate tuples, defining where on
          the board the bombs must be placed.
        """
        board = Board(layout.width, layout.height) 
        for x, y in layout.bomb_positions:
            board.place_bomb(x, y)
        return board


class Board(object):

    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.fields = [[0] * width for _ in range(0, height)]

    def place_bomb(self, x, y):
        self.fields[y][x] = '*'
        empty_positions_around_bomb = (
            (x, y) for x, y in self._positions_around(x, y)
            if self.fields[y][x] != '*'
        )
        for x, y in empty_positions_around_bomb:
            self.fields[y][x] += 1

    def _positions_around(self, x, y):
        return (
            (x+dx, y+dy) for dx, dy in [
                (-1,-1), (0,-1), (1,-1),
                (-1, 0),         (1, 0),
                (-1, 1), (0, 1), (1, 1)
            ] if 0 <= x+dx < self.width and \
                 0 <= y+dy < self.height
        )


class TestLayout(object):

    def __init__(self, layout):
        self.rows = layout
        self._validate_layout() 
        self._validate_contents() 
        self._set_bomb_positions()

    def _validate_layout(self):
        if (len(self.rows) < 2):
            raise ValueError(
                "Invalid board layout: the height should be at least 2")
        self.height = len(self.rows) - 2

        min_row_len = min(len(row) for row in self.rows)
        max_row_len = max(len(row) for row in self.rows)
        if (min_row_len != max_row_len):
            raise ValueError(
                "Invalid board layout: not all rows are of the same length")
        if (min_row_len < 2):
            raise ValueError(
                "Invalid board layout: the width should be at least 2")
        self.width = min_row_len - 2

    def _validate_contents(self):
        top_or_bottom_re = re.compile('^\+-{%d}\+$' % self.width)
        middle_re  = re.compile('^\|[ \*]{%d}\|$' % self.width)
        for row_idx, row in enumerate(self.rows):
            is_top_or_bottom_row = row_idx == 0 or row_idx == self.height + 1
            check_re = top_or_bottom_re if is_top_or_bottom_row else middle_re
            if not check_re.match("".join(row)):
                raise ValueError(
                    "Invalid board contents: invalid data on line %d" \
                    % (row_idx + 1))

    def _set_bomb_positions(self):
        self.bomb_positions = [
            (x, y)
            for y in xrange(0, self.height + 1)
            for x in xrange(0, self.width + 1)
            if self.rows[y+1][x+1] == '*'
        ]

    @staticmethod
    def rows_from_board(board):
        top = bottom = "+" + "-"*board.width + "+"
        rows = []
        rows.append(top)
        rows.extend(
            "|" + "".join(str(f) if f else " " for f in row) + "|"
            for row in board.fields
        )
        rows.append(bottom)
        return rows


class RandomLayout(object):

    def __init__(self, width, height, number_of_bombs):
        self.width = width
        self.height = height 
        self._validate_layout()
        self._set_bomb_positions(number_of_bombs)

    def _validate_layout(self):
        if self.width <= 0:
            raise ValueError("Invalid board layout: the width must be > 0")
        if self.height <= 0:
            raise ValueError("Invalid board layout: the height must be > 0")

    def _set_bomb_positions(self, number_of_bombs):
        all_positions = [
            (x, y) 
            for x in xrange(0, self.width)
            for y in xrange(0, self.height)
        ]
        self.bomb_positions = sample(all_positions, number_of_bombs)

import re

def board(layout):
    board = MinesweeperBoard(layout)
    return board.layout()

class MinesweeperBoard():

    def __init__(self, layout):
        self.rows = []
        for line in layout:
            self.rows.append(list(line))
        self._validate_layout() 
        self._validate_contents() 
        self._set_bomb_counts()

    def _validate_layout(self):
        self.height = len(self.rows)
        if (self.height < 2):
            raise ValueError(
                "Invalid board layout: the height should be at least 2")

        min_row_len = min(len(row) for row in self.rows)
        max_row_len = max(len(row) for row in self.rows)
        self.width = min_row_len
        if (min_row_len != max_row_len):
            raise ValueError(
                "Invalid board layout: not all rows are of the same length")
        if (self.width < 2):
            raise ValueError(
                "Invalid board layout: the width should be at least 2")

    def _validate_contents(self):
        top_or_bottom_re = re.compile('^\+-{%d}\+$' % (self.width - 2))
        middle_re  = re.compile('^\|[ \*]{%d}\|$' % (self.width - 2))
        for row_idx, row in enumerate(self.rows):
            is_top_or_bottom_row = row_idx == 0 or row_idx == self.height - 1
            check_re = top_or_bottom_re if is_top_or_bottom_row else middle_re
            if not check_re.match("".join(row)):
                raise ValueError(
                    "Invalid board contents: invalid data on line %d" \
                    % (row_idx + 1))

    def _set_bomb_counts(self):
        safe_fields = (
            (x, y)
            for y in xrange(1, self.height - 1)
            for x in xrange(1, self.width - 1)
            if not self._goes_boom(x, y)
        )
        for x, y in safe_fields:
            bomb_count = self._get_bomb_count(x, y)
            self.rows[y][x] = str(bomb_count) if bomb_count else " "

    def _get_bomb_count(self, x, y):
        return sum(
            1 for dx, dy in [
                (-1,-1), (0,-1), (1,-1),
                (-1, 0),         (1, 0),
                (-1, 1), (0, 1), (1, 1)
            ] if self._goes_boom(x+dx, y+dy))

    def _goes_boom(self, x, y):
        return self.rows[y][x] == '*'

    def layout(self):
        return [ "".join(row) for row in self.rows ]

    def to_string(self):
        return "\n".join(self.layout())

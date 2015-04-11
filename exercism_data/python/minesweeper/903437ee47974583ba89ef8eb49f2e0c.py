CORNER = '+'
HORIZONTAL_BORDER = '-'
VERTICAL_BORDER = '|'

EMPTY = ' '
MINE = '*'


class Board(object):
    def __init__(self, rows):
        self._validate(rows)
        self.rows = rows

    def _build_horizontal_border(self, width):
        return CORNER + HORIZONTAL_BORDER * width + CORNER

    def _validate(self, rows):
        """Check that the input format looks valid"""
        # check 1
        if not all(len(row) == len(rows[0]) for row in rows):
            raise ValueError('Rows are expected to have equal length.')

        # check 2
        border = self._build_horizontal_border(len(rows[0]) - 2)
        if not (
            rows[0] == border and rows[-1] == border and
            all(row[0] == row[-1] == VERTICAL_BORDER for row in rows[1:-1])
        ):
            raise ValueError('Border is not as expected.')

        # check 3
        if not all(item in (EMPTY, MINE) for row in rows[1:-1] for item in row[1:-1]):
            raise ValueError('Board contains a non-expected character.')

    def _compute_cell_digit(self, x, y):
        item = self.rows[x][y]
        if item != ' ':
            return item
        mine_sum = sum(
            self.rows[x + i][y + j] == '*' for i in range(-1, 2) for j in range(-1, 2)
        )
        return mine_sum if mine_sum > 0 else ' '

    def build_rows_with_digits(self):
        return [
            ''.join(
                str(self._compute_cell_digit(x, y))
                for y, __ in enumerate(row)
            )
            for x, row in enumerate(self.rows)
        ]


def board(rows):
    return Board(rows).build_rows_with_digits()

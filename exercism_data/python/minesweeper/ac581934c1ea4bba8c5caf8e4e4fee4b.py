def board(raw_board):
    return Board(raw_board).hint()


class Board:
    VALID_BORDERS = ["+", "-", "|"]
    VALID_DATA = ["*", "|", "+", "-", " "]

    def __init__(self, raw_board):
        self.rows = raw_board
        self.__validate()

    def hint(self):
        printout = []
        for i, row in enumerate(self.rows):
            printout.append(self.__hint_row(i, row))
        return printout

    def __hint_row(self, i, row):
        inner = []
        for j, tile in enumerate(row):
            inner.append(self.__hint_tile(tile, i, j, row))
        return "".join(inner)

    def __hint_tile(self, tile, i, j, row):
        if tile is not " ":
            return tile
        count = self.__get_bomb_count(row, i, j)
        if count > 0:
            return str(count)
        return tile

    def __get_bomb_count(self, row, i, j):
        return sum(datum is "*" for datum in
                   self.__surrounding_squares(row, i, j))

    def __surrounding_squares(self, row, i, j):
        return [row[j - 1], row[j + 1],
                self.rows[i - 1][j - 1], self.rows[i - 1][j],
                self.rows[i - 1][j + 1], self.rows[i + 1][j - 1],
                self.rows[i + 1][j], self.rows[i + 1][j + 1]]

    def __validate(self):
        self.__validate_row_lengths()
        self.__validate_borders()
        self.__validate_tiles()

    def __validate_row_lengths(self):
        length = len(self.rows[0])
        [self.__raise_invalid() for row in self.rows if len(row) is not length]

    def __validate_borders(self):
        for row in ([self.rows[0], self.rows[-1]]):
            [self.__raise_invalid() for char in row
             if char not in self.VALID_BORDERS]

    def __validate_tiles(self):
        for row in self.rows:
            [self.__raise_invalid() for tile in row
             if tile not in self.VALID_DATA]

    def __raise_invalid(self):
        raise ValueError("Invalid board")

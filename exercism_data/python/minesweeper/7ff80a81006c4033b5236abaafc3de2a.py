def board(raw_board):
    return Board(raw_board).printout()

class Board:
    VALID_BORDERS = ["+", "-", "|"]
    VALID_DATA = ["*", "|", "+", "-", " "]

    def __init__(self, raw_board):
        self.rows = raw_board
        self._validate()

    def printout(self):
        printout = []
        for i, row in enumerate(self.rows):
            inner = []
            for j, space in enumerate(row):
                if space is not " ":
                    inner.append(space)
                else:
                    counter = 0
                    for datum in ([row[j -1], row[j + 1], self.rows[i - 1][j - 1], self.rows[i - 1][j], self.rows[i - 1][j + 1], self.rows[i + 1][j - 1], self.rows[i + 1][j], self.rows[i + 1][j + 1]]):
                        if datum is "*":
                            counter += 1
                    if counter > 0:
                        inner.append(str(counter))
                    else:
                        inner.append(space)
            printout.append("".join(inner))
        return printout

    def _validate(self):
        length = len(self.rows[0])
        if not all(len(row) is length for row in self.rows):
            raise ValueError("Invalid board")

        for row in ([self.rows[0], self.rows[-1]]):
            for char in row:
                if char not in self.VALID_BORDERS:
                    raise ValueError("Invalid board")

        for row in self.rows:
            for char in row:
                if char not in self.VALID_DATA:
                    raise ValueError("Invalid board")

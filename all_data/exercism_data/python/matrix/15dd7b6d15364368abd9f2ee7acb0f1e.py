class Matrix(object):
    """A class that allows construction of a
    matrix using a line-separated input string,
    followed by queries by row and column."""
    def __init__(self, string):
        self.rows = self.parse_input(string)
        self.columns = [[row[i] for row in self.rows]
                        for i in range(len(self.rows[0]))]

    def parse_input(self, string):
        char_rows = string.split("\n")
        return [list(map(int, row_string.split(" "))) for row_string in char_rows]

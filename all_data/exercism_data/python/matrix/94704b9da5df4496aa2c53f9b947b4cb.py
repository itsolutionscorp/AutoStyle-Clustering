class Matrix:

    rows = []
    columns = []

    def __init__(self, string):
        self.rows = self.__from_string_to_rows(string)
        self.columns = self.__transpose_matrix(self.rows)

    def __from_string_to_rows(self, string):
        return [[int(cell) for cell in row.split()] for row in string.split("\n")]

    def __transpose_matrix(self, matrix):
        return [list(i) for i in zip(*matrix)]

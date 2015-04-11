class Matrix:

    rows = []
    columns = []

    def __init__(self, string):
        self.rows = self.__from_string_to_rows(string)
        self.columns = self.__transpose_matrix(self.rows)

    def __from_string_to_rows(self, string):
        rows = [row.split(" ") for row in string.split("\n")]
        return self.__from_string_rows_to_int_rows(rows)

    def __from_string_rows_to_int_rows(self, rows):
        return [[int(cell) for cell in row] for row in rows]

    def __transpose_matrix(self, matrix):
        return [list(i) for i in zip(*matrix)]

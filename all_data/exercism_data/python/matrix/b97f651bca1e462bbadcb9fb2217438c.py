class Matrix:

    class ColumnsProcessor:
        def __getitem__(self, key):
            return [self.rows[dim][key] for dim in range(len(self.rows))]

    def make_rows(self, matrix_as_str):
        str_rows = matrix_as_str.split("\n")
        self.rows = [[int(n) for n in _.strip().split(" ")] for _ in str_rows]

    def __init__(self, matrix_as_str):
        self.make_rows(matrix_as_str)
        self.columns = self.ColumnsProcessor()
        self.columns.rows = self.rows

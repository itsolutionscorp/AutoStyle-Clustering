class Matrix:

    def __init__(self, matrix_string):
        self.rows = [[int(num) for num in row.split()] for row in matrix_string.splitlines()]
        self.columns = [list(x) for x in zip(*self.rows)]

class Matrix:
    def __init__(self, matrix):
        self.rows = [[int(i) for i in line.split()] for line in matrix.splitlines()]
        self.columns = [list(column) for column in zip(*self.rows)]

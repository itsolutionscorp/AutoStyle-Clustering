class Matrix:

    matrix = []
    colcount = 0

    def __init__(self, matrix):
        self.matrix = [[int(i) for i in row.split()] for row in matrix.splitlines()]
        if 0 < len(self.matrix):
            self.colcount = len(self.matrix[0])

    @property
    def rows(self):
        return self.matrix

    @property
    def columns(self):
        return [[column[i] for column in self.matrix] for i in range(self.colcount)]

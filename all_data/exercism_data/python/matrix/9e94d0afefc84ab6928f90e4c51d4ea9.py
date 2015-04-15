class Matrix(object):
    def __init__(self, matrix_string):
        self.rows = [[int(elem) for elem in row.split()]
                     for row in matrix_string.split('\n')]

    @property
    def columns(self):
        return [list(tup) for tup in zip(*self.rows)]

class Matrix(object):
    """docstring for Matrix"""
    def __init__(self, matrix_string):
        super(Matrix, self).__init__()
        self.rows = [[int(e) for e in row.split()] for row in matrix_string.split('\n')]
        self.columns = [list(column) for column in zip(*self.rows)]

class Matrix(object):
    def __init__(self, matrix):
        self.rows = []
        self.columns = []
        if not matrix:
            return
        for i, line in enumerate(matrix.split('\n')):
            self.rows.append([int(x) for x in line.split()])

        self.columns = [list(x) for x in zip(*self.rows)]

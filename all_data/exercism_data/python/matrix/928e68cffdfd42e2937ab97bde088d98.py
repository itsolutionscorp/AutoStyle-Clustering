class Matrix(object):
    def __init__(self, matrix):
        self.rows = [
            [int(y) for y in x.split(' ')] for x in matrix.split('\n')
            ]
        self.columns = [
            [self.rows[i][j] for i in range(len(self.rows))]
            for j in range(len(self.rows[0]))
            ]

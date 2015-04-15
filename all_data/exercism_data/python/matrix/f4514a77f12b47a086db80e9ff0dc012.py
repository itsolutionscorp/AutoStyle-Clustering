class Matrix(object):

    def __init__(self, matrix_str):
        '''Build matrix model as a list of lists'''
        self.matrix = []
        for row in matrix_str.splitlines():
            self.matrix.append([int(s) for s in row.strip().split(' ')])

    @property
    def rows(self):
        '''Return matrix rows as a list of lists'''
        return self.matrix

    @property
    def columns(self):
        '''Return matrix columns as a list of lists'''
        return [[r[i] for r in self.matrix] for i in range(len(self.matrix[0]))]

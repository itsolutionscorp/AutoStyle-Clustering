class Matrix(object):

    def __init__(self, matrix):
        lines = matrix.split('\n')
        self.rows = [self.process_row(line) for line in lines]
        self.columns = [list(i) for i in zip(*self.rows)]

    def process_row(self, row):
        return [int(i) for i in row.strip().split(' ')]

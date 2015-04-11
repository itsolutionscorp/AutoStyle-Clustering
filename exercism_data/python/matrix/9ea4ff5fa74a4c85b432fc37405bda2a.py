class Matrix(object):
    def __init__(self, descr):
        self.values = [[int(v) for v in line.split()] for line in descr.split('\n')]
        nrows = len(self.values)
        ncols = 0 if nrows == 0 else len(self.values[0])
        self.shape = (nrows, ncols)

    def __getitem__(self, (i, j)):
        return self.values[i][j]

    @property
    def rows(self):
        return self.values

    @property
    def columns(self):
        return [[self[j, i] for j in xrange(self.shape[0])] for i in xrange(self.shape[1])]

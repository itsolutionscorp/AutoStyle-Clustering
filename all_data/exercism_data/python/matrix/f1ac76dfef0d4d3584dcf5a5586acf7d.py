class Matrix:
    def __init__(self, m):
        self.rows = []
        self.columns = []

        for s in m.split('\n'):
            self.rows.append([int(x) for x in s.split()])
            for c in xrange(len(self.rows[-1])):
                if c >= len(self.columns):
                    self.columns.append([])
                self.columns[c].append(self.rows[-1][c])

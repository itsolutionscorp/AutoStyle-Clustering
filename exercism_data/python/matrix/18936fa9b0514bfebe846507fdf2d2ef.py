class Matrix:
    def __init__(self, m):
        self.rows = []
        self.columns = []

        for s in m.split('\n'):
            self.rows.append([int(x) for x in s.split()])

        for r in xrange(len(self.rows)):
            for c in xrange(len(self.rows[0])):
                if c >= len(self.columns):
                    self.columns.append([])
                self.columns[c].append(self.rows[r][c])

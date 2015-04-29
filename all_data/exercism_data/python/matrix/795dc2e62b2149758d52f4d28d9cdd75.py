class Matrix(object):
    def __init__(self, s):
        self.rows = [[int(e) for e in r.split()] for r in s.splitlines()]
        self.columns = ColumnContainer(self)

class ColumnContainer(object):
    def __init__(self, m):
        self.m = m
    def __getitem__(self, i):
        return [r[i] for r in self.m.rows]

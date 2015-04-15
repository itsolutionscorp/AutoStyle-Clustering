class Matrix(object):

    def __init__(self, m):
        self.rows = [[int(i) for i in row.split()] for row in m.splitlines()]

    @property
    def columns(self):
        return [list(c) for c in zip(*self.rows)]

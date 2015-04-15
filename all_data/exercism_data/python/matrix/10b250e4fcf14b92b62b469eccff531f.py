class Matrix(object):

    def __init__(self, plain):
        self.rows = [[int(i) for i in y.split()] for y in plain.split('\n')]

    @property
    def columns(self):
        rows = self.rows
        return [[rows[y][i] for y in range(len(rows))] for i in range(len(rows[0]))]

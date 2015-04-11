class Matrix(object):
    def __init__(self, string):
        self.rows = [map(int, row.split()) for row in string.splitlines()]
        self.columns = [list(col) for col in zip(*self.rows)]

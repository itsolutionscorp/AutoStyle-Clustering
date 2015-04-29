class Matrix(object):
    def __init__(self, representation):
        self.rows = [map(int, row.split()) for row in representation.splitlines()]
        self.columns = map(list, zip(*self.rows))

class Matrix(object):
    def __init__(self, string_repr):
        self.rows = [[int(n) for n in row.split()]
                     for row in string_repr.splitlines()]

    @property
    def columns(self):
        return [list(column) for column in zip(*self.rows)]

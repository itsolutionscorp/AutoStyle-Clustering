class Matrix(object):
    def __init__(self, representation):
        self.rows = []
        for line_representation in representation.split('\n'):
            self.rows.append([int(el) for el in line_representation.split()])

    @property
    def columns(self):
        return [list(row) for row in zip(*self.rows)]

class Matrix(object):
    def __init__(self, representation):
        self.values = [[int(number) for number in row.split()] for row in representation.splitlines()]

    @property
    def rows(self):
        return self.values

    @property
    def columns(self):
        return map(list, zip(*self.values))

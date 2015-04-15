class Matrix(object):
    def __init__(self, numbers):
        self.rows = [map(int, row.split()) for row in numbers.splitlines()]
        self.columns = map(list, zip(*self.rows))

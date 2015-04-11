class Matrix(object):
    def __init__(self, string):
        self.rows = [[int(x) for x in line.split()] for line in string.splitlines()]
        self.columns = [list(x) for x in zip(*self.rows)]

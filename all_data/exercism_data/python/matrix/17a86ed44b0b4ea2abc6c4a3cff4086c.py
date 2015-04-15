class Matrix:

    def __init__(self, block = ''):
        _rows = block.splitlines()
        self.rows = [[int(column) for column in row.split()] for row in _rows]

        _columns = zip(*self.rows)
        self.columns = [list(column) for column in _columns]

class Matrix:

    def __init__(self, string):
        self.rows = [[int(cell) for cell in row.split()] for row in string.split("\n")]
        self.columns = [list(i) for i in zip(*self.rows)]

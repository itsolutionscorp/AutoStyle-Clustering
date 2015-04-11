class Matrix:
    def __init__(self, string):
        self.rows = [[int(x) for x in row.split()] for row in string.split('\n')]
        self.columns = list(map(list, zip(*self.rows)))

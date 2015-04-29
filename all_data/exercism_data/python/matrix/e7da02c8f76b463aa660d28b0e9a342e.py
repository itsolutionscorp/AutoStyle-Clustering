class Matrix(object):
    def __init__(self, matString):
        self.rows = [[int(x) for x in row.split()] for row in matString.split('\n')]
        self.columns = [[row[i] for row in self.rows] for i in range(len(self.rows[0]))]

class Matrix:
    def __init__(self, matrix):
        self.rows = [[int(i) for i in line.split()] for line in matrix.split('\n')]
        self.columns = [[row[i] for row in self.rows] for i in range(len(self.rows[0]))]

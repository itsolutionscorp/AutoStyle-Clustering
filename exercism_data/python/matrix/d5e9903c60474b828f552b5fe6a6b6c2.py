class Matrix:
    def __init__(self, matrix):
        self.rows = list()
        for line in matrix.split('\n'):
            self.rows.append([int(i) for i in line.strip().split(' ')])

        self.columns = list()
        for c in range(len(self.rows[0])):
            col = list()
            for row in self.rows:
                col.append(row[c])
            self.columns.append(col)

__author__ = 'tracyrohlin'

class Matrix:
    def __init__(self, matrix):
        self.matrix = matrix.split("\n")
        self.rows = []
        for row in self.matrix:
            row = row.split()
            row_list = []
            for n in row:
                n = int(n)
                row_list.append(n)
            self.rows.append(row_list)
        self.columns = []
        for i in range(len(self.rows[1])):
            current_col = []
            for row in self.rows:
                current_col.append(row[i])
            self.columns.append(current_col)

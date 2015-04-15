class Matrix:

    def __init__(self, matrix_value):
        self.rows = self.create_rowlist(matrix_value)
        self.columns = self.create_columnlist(self.rows)

    def create_rowlist(self, matrix_value):
        all_rows = []
        for line in matrix_value.split('\n'):
            all_rows.append([int(n) for n in line.split(' ')])
        return all_rows

    def create_columnlist(self, row_matrix):
        transpose = zip(*row_matrix)
        return [list(i) for i in transpose]

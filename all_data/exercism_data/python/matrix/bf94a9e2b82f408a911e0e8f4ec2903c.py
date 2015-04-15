class Matrix:

    def __init__(self, matrix_string):

        self.rows = [map(int, x.split()) for x in matrix_string.split('\n')]

        self.columns = self._init_col_container(self.rows)

        for i, row in enumerate(self.rows):
            for j, col in enumerate(row):
                self.columns[j][i] = col

    def _init_col_container(self, rows):
        matrix_height = len(rows[0])
        matrix_width = len(rows)

        columns = [None]*matrix_height
        for i in range(matrix_height):
            columns[i] = [None]*matrix_width

        return columns

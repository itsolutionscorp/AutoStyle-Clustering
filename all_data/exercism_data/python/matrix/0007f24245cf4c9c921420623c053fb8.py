class Matrix():
    def __init__(self, matrix_str):
        self.rows = self.parse_matrix(matrix_str)
        self.columns = self.transpose(self.rows)

    def parse_matrix(self, matrix_str):
        matrix = []
        for row in matrix_str.split('\n'):
            matrix.append([int(el) for el in row.strip().split(' ')])

        return matrix

    def transpose(self, array):
        transposed_array = []
        for i in range(len(array) - 1):
            transposed_array.append([row[i] for row in array])
        return transposed_array

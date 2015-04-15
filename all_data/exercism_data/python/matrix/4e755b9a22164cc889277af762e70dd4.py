class Matrix(object):

    def __init__(self, str_matrix):
        self.rows = [[int(val) for val in row.split(' ')]
                     for row in str_matrix.split('\n')]
        length = len(self.rows[0])
        self.columns = [[row[i] for row in self.rows]
                        for i in range(length)]

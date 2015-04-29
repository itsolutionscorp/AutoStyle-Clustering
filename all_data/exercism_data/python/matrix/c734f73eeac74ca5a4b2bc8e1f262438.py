class Matrix(object):
    def __init__(self, matrix_str):
        self.rows = matrix_str.split('\n')
        self.columns = []
        for i in range(len(self.rows)):
            self.rows[i] = self.rows[i].split(' ')
            for j in range(len(self.rows[i])):
                self.rows[i][j] = int(self.rows[i][j])
                if i == 0:
                    self.columns.append([])
                self.columns[j].append(self.rows[i][j])

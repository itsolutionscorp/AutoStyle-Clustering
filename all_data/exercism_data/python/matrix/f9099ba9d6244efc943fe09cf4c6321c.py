class Matrix(object):
    def __init__(self,matrix):
        self.rows = []
        self.columns = []
        lines = matrix.splitlines()
        for line in lines:
            num_line = [int(n) for n in line.split()]
            self.rows.append(num_line)
        for i in range(0,len(self.rows[0])):
            self.columns.append([])
            for j in range(0,len(self.rows)):
                self.columns[i].append(self.rows[j][i])

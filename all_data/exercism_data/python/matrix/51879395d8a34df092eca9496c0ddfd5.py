class Matrix(object):
    def __init__(self, matrixString):
        self.rows = []
        self.columns = []

        lines = matrixString.split('\n')
        i = 0
        for line in lines:
            j = 0
            line = line.split(' ')
            self.rows.append([])
            for number in line:
                self.rows[i].append(int(number))
                try:
                    self.columns[j].append(int(number))
                except:
                    self.columns.append([])
                    self.columns[j].append(int(number))
                j += 1
            i += 1

class Matrix(object):
    def __init__(self, matrix):
        self.rows = [[int (v) for v in row.split()] \
                for row in matrix.splitlines()]
        self.columns = self.rows and [[row[i] for row in self.rows] 
                for i in range(len(self.rows[0]))]
        

class Matrix(object):
    def __init__(self,num_str):
        # We're not doing input error checking here.
        self.rows = [[int(num) for num in line.split()] for line in num_str.split('\n')]
        self.columns = [[row[col] for row in self.rows] for col in range(len(self.rows[0]))]

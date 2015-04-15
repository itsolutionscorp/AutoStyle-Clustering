class Matrix(object):

    def __init__(self, mat_string):
        self.rows = [[int(x) for x in row.strip().split(' ')] for row in mat_string.split('\n')] 
        self.columns =  [[self.rows[row][col] for row in range(0,len(self.rows))] for col in range(0,len(self.rows[0]) )]

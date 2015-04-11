class Matrix(object):
    
    rows=[]
    columns=[]
    
    def __init__(self, matrix):
        self.matrix = matrix
        self.rows = ([[int(s) for s in e.split()] for e in self.matrix.split('\n')])
        self.columns = [[g for g in s] for s in zip(*self.rows)]

class Matrix(object):
    
    def __init__(self,textmatrix):
        matrix = textmatrix.split('\n')
        
        self.rows = [map(int,x.split()) for x in matrix]
        
        self.colN = len(self.rows[0])
        
        self.columns = []
        for i in xrange(self.colN):
            self.columns.append([row[i] for row in self.rows])

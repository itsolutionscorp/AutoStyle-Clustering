class Matrix(object):
    '''
    Represent a Matrix providing easy access to rows and columns
    '''
    
    def __init__(self, mstr):
        # Split the given string into rows and rows into individual ints
        self.rows = [[int(n) for n in r.split()] for r in mstr.split('\n')]
        # Use Zip to convert the rows into columns
        self.columns = [list(t) for t in zip(*self.rows)]

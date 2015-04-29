class Matrix:

    def __init__(self, matrixText):
        self.rows = [ [int(x) for x in r.split()] for r in matrixText.split('\n') ]
        self.columns = [ list(l) for l in zip(*self.rows) ]

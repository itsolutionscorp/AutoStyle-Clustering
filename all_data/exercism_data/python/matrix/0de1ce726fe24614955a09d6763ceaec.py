class Matrix():
    def __init__(self,matrix):
        """Defines a matrix with a matrix descriptor"""
        self.rows = [list(map(int,line.split()))
                     for line in matrix.splitlines()]
        self.columns = [
                         [ line[idx]
                         for line in self.rows ]
                         for idx,_ in enumerate(self.rows[0])
                       ]

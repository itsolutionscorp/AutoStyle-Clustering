class Matrix:
    def __init__(self, m):
        self.rows    = [ [ int(n) for n in rs.split() ] for rs in m.split('\n') ]
        self.columns = [ [ r[ci]  for r in self.rows  ] for ci in range(len(self.rows[0])) ]

            

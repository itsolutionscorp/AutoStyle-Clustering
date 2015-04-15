class Matrix:
    def __init__(self, m):
        self.rows    = [ [ int(n) for n in rs.split() ] for rs in m.split('\n') ]
        self.columns = [ list(l)  for l in zip(*self.rows) ] # thanks @betegelse

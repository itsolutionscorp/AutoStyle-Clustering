class Matrix(object):
    def __init__(self, s):
        self.rows = [map(int, r.strip().split(' ')) for r in s.split('\n')]        
        self.columns = map(list, zip(*self.rows))

class Matrix:
    def __init__(self, s):
        self.rows = [[eval(c) for c in r.split(' ')] for r in s.split('\n')]
        self.columns = [[r[i] for r in self.rows] for i in
                range(len(self.rows[0]))]

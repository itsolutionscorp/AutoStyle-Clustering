class Matrix ():
    def __init__(self, mtx):
        self.rows = [[int(n) for n in l.split()]
                     for l in mtx.split('\n')]
        self.columns = [[self.rows[j][i] for j in range(len(self.rows))]
                        for i in range(len(self.rows[0]))]

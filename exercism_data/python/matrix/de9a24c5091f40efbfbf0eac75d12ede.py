class Matrix(object):

    def __init__(self, s):
        self.rows = [[int(d) for d in i.split()] for i in s.split('\n')]
        self.columns = [[] for i in range(len(self.rows[0]))]
        for i, a in enumerate(self.columns):
            for r in self.rows:
                a.append(r[i])

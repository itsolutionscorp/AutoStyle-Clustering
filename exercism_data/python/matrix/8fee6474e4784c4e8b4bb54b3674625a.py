class Matrix(object):
    def __init__(self, matrx):
        self.rows = [x.strip().split(" ") for x in matrx.split("\n")]
        self.rows = [[int(y) for y in x] for x in self.rows]
        if len(self.rows)>0:
            self.columns = [[] for x in self.rows[0]]
        for i in self.rows:
            for j in range(len(i)):
                self.columns[j].append(i[j])

class Matrix:
    car=staticmethod(lambda x:x[0])
    cdr=staticmethod(lambda x:x[1:])
    def __init__(self, s):
        self.rows=Matrix._parseMatrix(s)
        self.columns=Matrix._transpose(self.rows)
    @staticmethod
    def _parseMatrix(s):
        return map(lambda x: map(int, filter(len, x.split(" "))), s.split("\n"))

    @staticmethod
    def _transpose(l):
        if len(l[0]):
            return [map(Matrix.car, l)]+Matrix._transpose(map(Matrix.cdr,l))
        else:
            return []

class Matrix(object):
    def __init__(self,string):
        self.rows = [[int(j) for j in i.split(' ')] for i in string.split('\n')]
        self.columns = []
        for i in xrange(len(self.rows[0])):
            column = [self.rows[j][i] for j in xrange(len(self.rows))]
            self.columns.append(column)

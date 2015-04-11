class Matrix(object):
    
    def __init__(self, matrix):
       self.matrix = matrix 

    def get_rows(self):
        l = []
        for row in [ row for row in [ r.split(' ')
                    for r in self.matrix.splitlines() ] ]:
            l.append([ int(col) for col in row ])
        return l
    rows = property(get_rows)

    def get_cols(self):
        l = []
        for i in range(len(self.rows[0])):
            l.append([ row[i] for row in self.rows ])
        return l
    columns = property(get_cols)

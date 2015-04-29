class Matrix(object):
    def __init__(self, string):
        self.rows = []
        for row in string.split('\n'):
            r = []
            for num in row.split():
                r.append(int(num))
            self.rows.append(r)

        self.columns = []
        for i in range(0, len(self.rows[0])):
            col = []
            for row in self.rows:
                col.append(row[i])
            self.columns.append(col)

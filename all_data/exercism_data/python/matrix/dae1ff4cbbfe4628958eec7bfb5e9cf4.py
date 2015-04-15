class Matrix(object):

    def __init__(self, data):
        self.rows = list()

        for line in data.split("\n"):
            self.rows.append([])
            current_row = self.rows[-1]
            for c in line.split():
                current_row.append(int(c))

        self.size = len(self.rows[0])

        self.columns = [[row[i] for row in self.rows] for i in xrange(self.size) ]

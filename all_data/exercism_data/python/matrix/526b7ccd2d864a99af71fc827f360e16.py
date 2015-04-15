class Matrix(object):
    def __init__(self, data):
        self.rows = [[int(char) for char in line.split()] for line in data.split("\n")]
        self.columns = [[row[i] for row in self.rows] 
                                for i in xrange(self.size) ]

    @property
    def size(self):
        return len(self.rows[0])

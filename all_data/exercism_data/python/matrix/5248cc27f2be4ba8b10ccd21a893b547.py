class Matrix:
    def __init__(self, string):
        self.mstring = string
        self.rows = [[int(a) for a in x.split()] for x in self.mstring.splitlines()]
        self.columns = [[row[i] for row in self.rows] for i in range(len(self.rows[0]))]

class Matrix:

    def __init__(self, data):
        self.data = [[int(x) for x in part.split()]
                             for part in data.split('\n')]

    @property
    def columns(self):
        return [[row[i] for row in self.data]
                        for i in range(len(self.data[0]))]

    @property
    def rows(self):
        return self.data

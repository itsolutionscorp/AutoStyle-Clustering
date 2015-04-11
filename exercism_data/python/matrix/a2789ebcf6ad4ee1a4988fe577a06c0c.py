class Matrix:

    def __init__(self, data):
        self.data = [[int(x) for x in part.split()]
                             for part in data.split('\n')]

        self._columns = []

    @property
    def columns(self):
        if not self._columns:
            self._columns =  [[row[i] for row in self.data]
                              for i in range(len(self.data[0]))]
        return self._columns

    @property
    def rows(self):
        return self.data

class Matrix():
    def __init__(self, matrix):
        self.matrix = matrix

    @property
    def rows(self):
        if not hasattr(self, '_rows'):
            self._rows = [[int(x) for x in part.split()]
                          for part in self.matrix.split('\n')]
        return self._rows

    @property
    def columns(self):
        if not hasattr(self, '_columns'):
            self._columns =  [ list(col) for col in zip(*self.rows) ]
        return self._columns

class Matrix:
    matrix = []

    def __init__(self, matrix_str):
        self.matrix = self._convert(matrix_str)

    def _convert(self, string):
        matrix = []
        rows = string.split("\n")
        for row in rows:
            cols = row.split(" ")
            matrix.append(cols)
        return matrix

    def _toint(self, l):
        rows = []
        for row in l:
            rows.append([int(i) for i in row])
        return rows

    @property
    def rows(self):
        return self._toint(self.matrix)

    @property
    def columns(self):
        columns = map(list, zip(*self.matrix))
        return self._toint(columns)

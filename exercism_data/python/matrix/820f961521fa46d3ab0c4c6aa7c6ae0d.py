class Matrix:
    def __init__(self, raw):
        self.rows = self._parse(raw)
        self.columns = self._transpose(self.rows)

    def _parse(self, raw):
        return [[int(datum) for datum in row.split()]
                for row in raw.splitlines()]

    def _transpose(self, rows):
        return [list(row) for row in zip(*rows)]

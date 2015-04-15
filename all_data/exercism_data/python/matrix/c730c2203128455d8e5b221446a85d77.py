class Matrix:
    def __init__(self, raw):
        self.rows = self._parse(raw)
        self.columns = self._transpose(self.rows)

    def _parse(self, raw):
        return [[int(string) for string in row.split()] for row in raw.split("\n")]

    def _transpose(self, rows):
        return [list(row) for row in zip(*list(rows))]

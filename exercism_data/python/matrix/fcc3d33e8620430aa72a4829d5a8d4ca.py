class Matrix:
    def __init__(self, raw):
        self.rows = self._parse(raw)
        self.columns = self._find_columns(self.rows)

    def _parse(self, raw):
        rows = str.split(raw, "\n")
        cols = map(str.split, rows)
        return [map(int, col) for col in cols]

    def _find_columns(self, rows):
        return map(list, zip(*rows))

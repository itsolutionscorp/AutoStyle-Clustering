class Matrix:
    def __init__(self, raw):
        self.rows = self._parse(raw)
        self.columns = self._transpose(self.rows)

    def _parse(self, raw):
        rows = str.split(raw, "\n")
        string_matrix = map(str.split, rows)
        return [list(map(int, string)) for string in string_matrix]

    def _transpose(self, rows):
        return list(map(list, zip(*rows)))

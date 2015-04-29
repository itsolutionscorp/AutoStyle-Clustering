class Matrix:
    def __init__(self, s):
        rowstrings = s.split('\n')
        def f(rs):
            return list(map(int, rs.split()))
        self.rows = list(map(f, rowstrings))
        self.columns = Columns(self.rows)
class Columns:
    def __init__(self, rows):
        self.rows = rows
    def __getitem__(self, n):
        def f(row):
            return row[n]
        return list(map(f, self.rows))

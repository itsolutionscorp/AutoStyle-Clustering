class Matrix:
    def __init__(self, s):
        self.data = [list(map(int, x.split())) for x in s.split('\n')]

    @property
    def rows(self):
        return self.data

    @property
    def columns(self):
        return [list(col) for col in zip(*self.data)]

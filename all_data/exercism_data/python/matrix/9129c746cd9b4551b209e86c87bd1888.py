class Matrix:
    def __init__(self, s):
        self.rows = [list(map(int, x.split())) for x in s.split('\n')]

    @property
    def columns(self):
        return [list(col) for col in zip(*self.rows)]

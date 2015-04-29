class Matrix:

    def __init__(self, string):
        self.rows = [list(map(int, i)) for i in [e.split() for e in string.split('\n')]]

    @property
    def columns(self):
        return list(map(list, zip(*self.rows)))

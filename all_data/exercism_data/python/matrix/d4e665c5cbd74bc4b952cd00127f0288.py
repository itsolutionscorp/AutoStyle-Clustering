class Matrix:
    def __init__(self, input):
        self.rows = [map(int, line.split()) for line in input.splitlines()]
        self.columns = map(list, zip(*self.rows))

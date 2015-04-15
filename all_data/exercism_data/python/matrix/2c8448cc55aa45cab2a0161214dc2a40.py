class Matrix:
    def __init__(self, input_string):
        self.rows = [
            map(int, row.strip().split(" "))
            for row in input_string.split("\n")
        ]
        self.columns = [list(x) for x in zip(*self.rows)]

        pass

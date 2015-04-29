class Matrix:

    def __init__(self, data):
        rows = []

        for line in data.split("\n"):
            row = list(map(int, line.split()))
            rows.append(row)

        self.rows = list(rows)
        self.columns = [list(column) for column in zip(*self.rows)]

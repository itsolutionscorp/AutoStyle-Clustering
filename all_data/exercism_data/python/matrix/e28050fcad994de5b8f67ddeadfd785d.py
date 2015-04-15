class Matrix:
    def __init__(self, input):
        self.rows = [list(map(int, row.split(' '))) for row in input.split("\n")]
        self.columns = [[self.rows[i][j] for i in range(len(self.rows))] for j in range(len(self.rows[0]))]

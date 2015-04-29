class Matrix:
    def __init__(self, input):
        self.rows = []
        self.columnss = []
        self.parseInput(input)

    def parseInput(self, input):
        lines = input.split("\n")
        limit = len(lines)
        self.rows = [list(map(int, line.split(' '))) for line in lines]
        self.columns = [[self.rows[i][j] for i in range(0, limit)] for j in range(0, len(self.rows[0]))]

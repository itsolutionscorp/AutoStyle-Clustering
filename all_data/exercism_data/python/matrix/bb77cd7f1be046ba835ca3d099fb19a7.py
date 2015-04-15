class Matrix:
    def __init__(self, array):
        self.rows = self.columns = []
        self.rows = [map(int, row.split(" ")) for row in array.split("\n")]
        self.columns = [[row[i] for row in self.rows] for i in range(len(self.rows[0]))]

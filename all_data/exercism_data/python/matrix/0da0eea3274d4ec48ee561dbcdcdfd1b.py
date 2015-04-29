class Matrix:

    def __init__(self, data):
        self.rows = data.split("\n")

        for n in range(0,len(self.rows)):
            ind = self.rows[n]
            self.rows[n] = map(int, ind.split(" "))

        self.columns = []

        for n in range(0, len(self.rows[n])):
            self.columns.append([])
            for num in range(0, len(self.rows)):
                self.columns[n].append(self.rows[num][n])

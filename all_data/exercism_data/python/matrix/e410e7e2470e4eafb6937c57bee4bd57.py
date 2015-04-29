class Matrix():
    def __init__(self, mtx):
        self.rows = []
        self.columns = []
        self.create_2d(mtx)

    def create_2d(self, mtx):
        row_strings = mtx.split('\n')

        for line in row_strings:
            self.rows.append([int(x) for x in line.split(' ')])

        for index in range(len(self.rows[0])):
            column = []
            for col in self.rows:
                column.append(col[index])
            self.columns.append(column)

class Column:
    def __init__(self, x, matrix):
        self.x = x
        self.matrix = matrix

    def __getitem__(self, y):
        return self.matrix.rows[y][self.x]

    def __setitem__(self, y, val):
        self.matrix.rows[y][self.x] = val
        return val

    def __len__(self):
        return len(self.matrix.rows)

    def __eq__(self, a_list):
        return all(self[i] == a_list[i] for i in range(len(self)))


class ColumnView:
    def __init__(self, matrix):
        self.matrix = matrix

    def __getitem__(self, index):
        return Column(index, self.matrix)

    def __setitem__(self, index, iterable):
        for i, value in enumerate(iterable):
            self.matrix[i][index] = value
        return Column(index, self.matrix)

    def __len__(self):
        return len(self.matrix[0])


class Matrix:
    def __init__(self, matrix_as_text):
        self.rows = [[int(cell) for cell in row.split()]
                     for row in matrix_as_text.splitlines()]

    @property
    def columns(self):
        return ColumnView(self)

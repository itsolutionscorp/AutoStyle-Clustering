__author__ = 'nebur1989'


class Matrix():

    def __init__(self, mat):
        self.rows = []
        for line in mat.split('\n'):
            row = []
            for element in line.split(' '):
                row.append(int(element))
            self.rows.append(row)
        cols = zip(*self.rows)
        self.columns = []
        for col in cols:
            self.columns.append(list(col))

    def rows(self):
        return self.rows

    def columns(self):
        return self.columns

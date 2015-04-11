__author__ = 'angelo'


class Matrix:

    def __init__(self, nums):
        self.rows = []
        for r in nums.split('\n'):
            row = []
            for n in r.split():
                row.append(int(n))
            self.rows.append(row)

        self.columns = []
        for i in range(len(self.rows[0])):
            column = []
            for j in range(len(self.rows)):
                column.append(self.rows[j][i])
            self.columns.append(column)

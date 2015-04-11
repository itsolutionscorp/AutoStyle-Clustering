__author__ = 'emiller42'


class Matrix:

    def __init__(self, input_list):

        self.rows = []
        self.columns = []

        row_count = 0
        col_count = 0

        for row in input_list.split('\n'):
            self.rows.append([])
            col_count = 0
            for item in row.split(' '):
                self.rows[row_count].append(int(item))
                col_count += 1
            row_count += 1

        for col in xrange(0, col_count):
            self.columns.append([])
            for row in xrange(0, row_count):
                self.columns[col].append(self.rows[row][col])

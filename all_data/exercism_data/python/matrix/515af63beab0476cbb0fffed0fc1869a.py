import string


class Matrix():

    def __init__(self, elements):
        raw_rows = string.split(elements, "\n")

        self.rows = range(len(raw_rows))
        self.columns = [[] for i in range(len(raw_rows[0].split()))]
        self.__assign_values(raw_rows)

    def __assign_values(self, raw_rows):
        for row_index, row in enumerate(raw_rows):
            self.rows[row_index] = [int(n) for n in row.split()]
            for col_index, value in enumerate(row.split()):
                self.columns[col_index].append(int(value))

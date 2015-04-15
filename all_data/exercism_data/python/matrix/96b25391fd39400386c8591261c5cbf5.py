class Matrix(object):
    """Represents an nxm matrix. Allows access by rows or columns."""

    def __init__(self, string_matrix):
        """Creates rows and columns lists from the given string"""
        self.rows = []
        for row in string_matrix.split('\n'):
            self.rows.append([int(value) for value in row.split()])

        self.columns = []
        for column in range(len(self.rows[0])):
            self.columns.append([self.rows[row][column]
                                 for row in range(len(self.rows))])

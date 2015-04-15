class Matrix(object):
    "Third python exercise in exercism"

    def __init__(self, unparsed_matrix):
        "Convert a string representaion into a matrix"
        irow = 0
        self.rows = []
        self.columns = []

        for unparsed_row in unparsed_matrix.splitlines():
            icol = 0
            for unparsed_col in unparsed_row.split():
                value = int(unparsed_col)

                if len(self.rows) > irow:
                    row = self.rows[irow]
                else:
                    row = []
                    self.rows.append(row)
                row.append(value)

                if len(self.columns) > icol:
                    column = self.columns[icol]
                else:
                    column = []
                    self.columns.append(column)
                column.append(value)

                icol += 1

            irow += 1

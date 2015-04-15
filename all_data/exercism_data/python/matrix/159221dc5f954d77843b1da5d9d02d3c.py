class Matrix(object):
    def __init__(self, mtx_str):
        str_rows = mtx_str.split("\n")
        self.rows = [ [ int(num) for num in row.split(" ") ] for row in str_rows ]

        column_counts = set()
        for row in self.rows:
            column_counts.add(len(row))
        if len(column_counts) > 1:
            # not all rows have the same number of columns
            # this is just a sanity check, probably will never be tripped
            raise ValueError("rows are not all the same length")

        columns = column_counts.pop()
        self.columns = [ [ self.rows[i][j] for i in range(len(self.rows)) ] for j in range(columns) ]

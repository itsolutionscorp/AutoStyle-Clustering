class Matrix(object):
    def __init__(self, matrix):
        self.rows = []
        self.columns = []

        for row in matrix.split('\n'):
            self.rows.append(
                [int(n) for n in row.split()]
            )

        nb_rows = len(self.rows)
        nb_cols = len(self.rows[0])

        if nb_rows:
            for c in xrange(nb_cols):
                self.columns.append(
                    [self.rows[r][c] for r in xrange(nb_rows)]
                )

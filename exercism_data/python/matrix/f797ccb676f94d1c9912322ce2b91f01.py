class Matrix(object):
    def __init__(self, matrix):
        self._parse_rows(matrix)
        if self.rows:
            self._generate_columns()

    def _parse_rows(self, matrix):
        self.rows = [
            [int(n) for n in row.split()]
            for row in matrix.split('\n')
        ]

    def _generate_columns(self):
        nb_rows = len(self.rows)
        nb_cols = len(self.rows[0])

        self.columns = [
            [self.rows[r][c] for r in xrange(nb_rows)]
            for c in xrange(nb_cols)
        ]

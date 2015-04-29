class Matrix(object):
    def __init__(self, init):
        split_at_newline = lambda m: map(lambda s: s.split(), m.split('\n'))
        convert_to_int   = lambda m: map(lambda s: int(s), m)
        column_range     = lambda m: range(len(m))
        column_member    = lambda x, m: map(lambda s: s[x], m)

        self.rows    = [convert_to_int(row)           for row in split_at_newline(init)]
        self.columns = [column_member(col, self.rows) for col in column_range(self.rows[0])]

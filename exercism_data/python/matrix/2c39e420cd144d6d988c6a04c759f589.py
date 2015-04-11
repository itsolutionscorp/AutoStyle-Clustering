# vim:fileencoding=utf-8


class Matrix(object):

    def __init__(self, string_or_rows):
        if hasattr(string_or_rows, 'append'):
            self.rows = string_or_rows
        else:
            self.rows = self.fromstring(string_or_rows).rows

    @property
    def columns(self):
        return self.rotate_90(self).rows

    @classmethod
    def fromstring(cls, matrix_string):
        return cls([
            map(lambda s: int(s.strip()), line.split())
            for line in matrix_string.splitlines(False)
        ])

    @classmethod
    def rotate_90(cls, matrix):
        if not len(matrix.rows):
            return cls(matrix.rows)

        output_rows = []

        for i in range(len(matrix.rows[0])):
            rotated = []
            for row in matrix.rows:
                rotated.append(row[i])
            output_rows.append(rotated)

        return cls(output_rows)

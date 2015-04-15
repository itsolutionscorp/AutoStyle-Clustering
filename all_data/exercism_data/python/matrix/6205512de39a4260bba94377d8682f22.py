class Matrix(object):
    def __init__(self, string):
        row_strings = string.split('\n')
        self.rows = [[int(e) for e in row.split()] for row in row_strings]
        self.columns = _transpose(self.rows)

def _transpose(rows):
    width = len(rows[0])
    height = len(rows)
    return [[rows[j][i] for j in range(height)] for i in range(width)]

class Matrix(object):
    def __init__(self, input_):
        self._rows = []
        txt_rows = input_.split('\n')
        for row_data in txt_rows:
            row_values = tuple(map(int, row_data.split()))
            self._rows.append(row_values)
    
    @property
    def rows(self):
        return [list(row) for row in self._rows]

    @property
    def columns(self):
        return [list(col) for col in zip(*self._rows)]

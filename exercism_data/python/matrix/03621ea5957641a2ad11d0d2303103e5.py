class Matrix:

    def __init__(self, data):
        """Create a matrix object and fill it with the provided data.
        The data must be the matrix in ASCII format, with embedded newlines,
        e.g. '1 2 3\n4 5 6'.
        """
        self.height = 0
        self.width = 0
        self._data = []
        for row in data.split("\n"):
            self.height += 1
            row_values = [int(v) for v in row.strip().split()]
            if not self.width:
                self.width = len(row_values)
            elif self.width != len(row_values):
                raise ValueError(
                    "Row %d has an unexpected number of values" % self.height)
            self._data.extend(row_values)

    @property
    def rows(self):
        """A list of matrix rows."""
        return [
            self._data[row*self.width : (row+1)*self.width]
            for row in xrange(0, self.height)
        ]

    @property
    def columns(self):
        """A list of matrix columns."""
        return [
            self._data[column :: self.width]
            for column in xrange(0, self.width)
        ]

    def _data_index(self, column, row):
        if row < 0 or row > self.height:
            raise IndexError("Row %d does not exist" % row)
        if column < 0 or column > self.width:
            raise IndexError("Column %d does not exist" % column)
        return column + row * self.width

    def get(self, column, row):
        """Returns the value from the matrix at coordinate (column, row)."""
        return self._data[self._data_index(column, row)]

    def set(self, column, row, value):
        """Set the value for the matrix at coordinate (column, row)."""
        self._data[self._data_index(column, row)] = value

    def sum(self):
        """Returns the sum of all values in the matrix."""
        return sum(self._data)

def saddle_points(data):
    return set(Matrix(data).saddle_points())

class Matrix:

    def __init__(self, data):
        """Create a matrix object and fill it with the provided data.
        The data must use one of the following formats:
          * string, with embedded newlines (e.g. '1 2 3\n4 5 6')
          * list-of-lists (e.g. [[1, 2, 3], [4, 5, 6]])
        """
        fill = self._fill()
        fill.next()
        for row in self._rows_from_data(data):
            fill.send(row)
        fill.close()

    def _rows_from_data(self, data):
        if isinstance(data, basestring):
            return self._rows_from_string(data)
        else:
            return self._rows_from_list(data)

    def _rows_from_string(self, data):
        for row in data.split("\n"):
            row_values = [int(v) for v in row.strip().split()]
            yield row_values

    def _rows_from_list(self, data):
        for row_values in data:
            yield row_values 

    def _fill(self):
        self.height = 0
        self.width = 0
        self._data = []

        while True:
            row = (yield)
            self.height += 1
            if not self.width:
                self.width = len(row)
            elif self.width != len(row):
                raise ValueError(
                    "Row %d has an unexpected number of values" % self.height)
            self._data.extend(row)

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

    def _data_index(self, coordinate):
        row, column = coordinate
        if row < 0 or row > self.height:
            raise IndexError("Row %d does not exist" % row)
        if column < 0 or column > self.width:
            raise IndexError("Column %d does not exist" % column)
        return column + row * self.width

    def get(self, coordinate):
        """Returns the value from the matrix at coordinate (row, column)."""
        return self._data[self._data_index(coordinate)]

    def set(self, coordinate, value):
        """Set the value for the matrix at coordinate (row, column)."""
        self._data[self._data_index(coordinate)] = value

    def sum(self):
        """Returns the sum of all values in the matrix."""
        return sum(self._data)

    def saddle_points(self):
        """Generates the coordinates for the saddle points in the matrix.
        Saddle points are points in the matrix where the value is greater than
        or equal to every element in its row and less than or equal to every
        element in its column.
        """
        for row, row_values in enumerate(self.rows):
            for column, column_values in enumerate(self.columns):
                if max(row_values) == row_values[column] and \
                   min(column_values) == column_values[row]:
                    yield (row, column)

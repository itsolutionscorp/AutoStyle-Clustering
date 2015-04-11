__author__ = 'Johnny Dave'


class Matrix(object):
    def __init__(self, data):
        self._data = data
        self.rows = []
        self.columns = []

        self._creatematrix()

    def _creatematrix(self):
        temp = []
        temp_row = []
        self._data = self._data.split("\n")
        for row in self._data:
            temp.append(row.strip())

        self._data = temp
        temp = []

        for item in self._data:
            temp.append((item.split(" ")))

        for row in temp:
            for item in row:
               temp_row.append(int(item))
            self.rows.append(temp_row)
            temp_row = []

        self.columns = self._transpose_list(self.rows)

    def _transpose_list(self, lst):
        """
        Transposes a given list.  The list must be in the for of an m by n Matrix.

        Created: 06/04/14

        Inputs:
        lst -- List to be transposed

        Outputs:
        transposed_list -- transposed list
        """
        index = 0
        transposed_list = []

        # Makes a row for every column
        for x in xrange(len(lst[0])):
            transposed_list.append([])

        # Transposes the list
        for line in lst:
            for item in line:
                transposed_list[index].append(item)
                index += 1
            index = 0

        return transposed_list

if __name__ == "__main__":
    matrix = Matrix("1 2 3\n4 5 6\n7 8 9\n 8 7 6")

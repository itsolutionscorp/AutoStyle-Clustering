# -*- coding: utf-8 -*-

class Matrix:
    """Build a matrix from its textual representation.

    Args:
      mat (str): Textual representation of the matrix. Rows are separated
        by a newline character. Whitespace separates values on a row.

    Attributes:
      rows (list of lists of int): Each list element is a row of the matrix.
      columns (list of lists of int): Each list element is a column of
        the matrix.
    """
    def __init__(self, mat):
        if not isinstance(mat, str):
            raise TypeError("Matrix must be string")

        self._parse(mat)

    def _parse(self, mat):
       try:
           self.rows = [[int(el) for el in row.split()]
                        for row in mat.splitlines()]
       except ValueError as inst:
           print("Matrix elements must be integers: {0}".format(inst))
       else:
           self.columns = [list(col) for col in zip(*self.rows)]

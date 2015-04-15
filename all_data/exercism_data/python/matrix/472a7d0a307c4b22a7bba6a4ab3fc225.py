"""A program that returns the rows and columns of a matrix."""

from itertools import izip


class Matrix(object):
    """A matrix.

    :ivar rows: list of the rows
    """
    def __init__(self, rows):
        """Create a matrix.

        :param rows: string representing a matrix of numbers
        :type rows: str
        """
        self.rows = [
            [int(entry) for entry in row.split()]
            for row in rows.split("\n")
        ]

    @property
    def columns(self):
        """list of the columns"""
        return [list(column) for column in izip(*self.rows)]

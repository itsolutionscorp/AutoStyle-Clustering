#!/usr/bin/env python3

class Matrix(object):
    def __init__(self, matrix):
        self._matrix = matrix
        self._rows = [[int(entry) for entry in row.split(' ')] for row in
                self._matrix.split('\n')]
        self._columns = [list(column) for column in zip(*self._rows)]

    @property
    def rows(self):
        return self._rows

    @property
    def columns(self):
        return self._columns

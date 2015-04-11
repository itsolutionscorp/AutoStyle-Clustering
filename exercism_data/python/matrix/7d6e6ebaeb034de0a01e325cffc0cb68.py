#!/usr/bin/env python3

class Matrix(object):
    def __init__(self, matrix):
        self._matrix = matrix

    @property
    def rows(self):
        return [[int(entry) for entry in row.split(' ')] for row in
                self._matrix.split('\n')]

    @property
    def columns(self):
        return list(map(list, list(zip(*self.rows))))

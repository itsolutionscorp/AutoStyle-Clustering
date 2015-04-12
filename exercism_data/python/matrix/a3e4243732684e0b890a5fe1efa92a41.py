# -*- coding: utf-8 -*-

class Matrix:

    def __init__(self, matrix: str):
        self.rows = [[int(elem)
                      for elem in row.split()]
                     for row in matrix.split('\n')]
        self.columns = self._transpose(self.rows)

    def _transpose(self, xs: list) -> list:
        return [list(t) for t in zip(*xs)]
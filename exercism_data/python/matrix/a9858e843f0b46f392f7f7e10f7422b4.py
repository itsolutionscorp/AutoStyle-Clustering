# -*- coding: utf-8 -*-

class Matrix:

    def __init__(self, matrix: str):
        self._matrix = [[int(elem)
                         for elem in row.strip().split(' ')]
                        for row in matrix.split('\n')]

    @property
    def rows(self) -> list:
        return self._matrix

    @property
    def columns(self) -> list:
        return self._transpose(self.rows)

    def _transpose(self, xs: list) -> list:
        return list(map(list, zip(*xs)))

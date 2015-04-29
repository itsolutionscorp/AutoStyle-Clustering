#!/usr/bin/env python


class Matrix(object):

    class Rows(object):
        def __init__(self, matrix):
            self.matrix = matrix
        def __getitem__(self, index):
            return self.matrix[index]

    class Columns(object):
        def __init__(self, matrix):
            self.matrix = matrix
        def __getitem__(self, index):
            return [row[index] for row in self.matrix]

    def __init__(self, text):
        self.matrix = [];
        for r in text.split('\n'):
            self.matrix.append([int(x) for x in r.split()])
        self.rows = Matrix.Rows(self.matrix)
        self.columns = Matrix.Columns(self.matrix)

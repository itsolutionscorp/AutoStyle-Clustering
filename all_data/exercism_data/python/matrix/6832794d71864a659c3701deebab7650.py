#!/usr/bin/env python


class Matrix(object):

    class Rows(object):
        def __init__(self, matrix):
            self.matrix = matrix

        def __getitem__(self, index):
            return self.matrix.data[index]

    class Columns(object):
        def __init__(self, matrix):
            self.matrix = matrix

        def __getitem__(self, index):
            return [row[index] for row in self.matrix.data]

    def __init__(self, text):
        self.data = []
        for r in text.split('\n'):
            self.data.append([int(x) for x in r.split()])
        self.rows = Matrix.Rows(self)
        self.columns = Matrix.Columns(self)

    def reassign(self, text):
        self.data = []
        for r in text.split('\n'):
            self.data.append([int(x) for x in r.split()])


def main():
    text = "1 2 3\n4 5 6\n7 8 9\n 8 7 6"
    matrix = Matrix(text)
    print("\n" + text)
    print(matrix.columns[0])
    print(matrix.rows[0])

    text = "89 1903 3\n18 3 1\n9 4 800"
    matrix.reassign(text)
    print("\n" + text)
    print(matrix.columns[0])
    print(matrix.rows[0])

if __name__ == '__main__':
    main()

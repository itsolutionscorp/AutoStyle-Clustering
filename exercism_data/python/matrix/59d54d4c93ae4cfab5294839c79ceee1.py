from __future__ import print_function

import argparse


class Matrix(object):
    def __init__(self, matrix):
        rows = matrix.split('\n')
        self.rows = list(map(lambda x: list(map(int, x.split())), rows))
        self.columns = list(map(list, zip(*self.rows)))

    def __repr__(self):
        rows = '\n'.join(' '.join(row) for row in self.rows)
        cols = '\n'.join(' '.join(col) for col in self.columns)
        return '\n\n'.join([rows,cols])


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('matrix', type=str)
    args = parser.parse_args()

    print(Matrix(args.matrix))

if __name__ == '__main__':
    main()

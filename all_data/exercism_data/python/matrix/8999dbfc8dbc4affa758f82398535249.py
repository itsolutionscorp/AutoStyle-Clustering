import argparse


class Matrix(object):
    def __init__(self, matrix):
        rows = [row.split() for row in matrix.split('\n')]
        self.rows = [[int(i) for i in row] for row in rows]
        self.columns = [list(col) for col in zip(*self.rows)]

    def __repr__(self):
        rows = '\n'.join([''.join(str(row)) for row in self.rows])
        cols = '\n'.join([''.join(str(col)) for col in self.columns])
        return '\n\n'.join([rows, cols])


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('matrix', type=str)
    args = parser.parse_args()

    m = Matrix(args.matrix)
    print(m)

if __name__ == '__main__':
    main()

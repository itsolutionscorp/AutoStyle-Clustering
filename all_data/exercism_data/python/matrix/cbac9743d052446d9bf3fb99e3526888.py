import argparse


class Matrix(object):
    def __init__(self, matrix):
        rows = matrix.split('\n')
        self.rows = map(lambda x: map(int, x.split()), rows)
        self.columns = []
        num_cols = len(self.rows[0])
        for i in xrange(num_cols):
            col = [row[i] for row in self.rows]
            self.columns.append(col)

    def __repr__(self):
        rows = '\n'.join(' '.join(row) for row in self.rows)
        cols = '\n'.join(' '.join(col) for col in self.columns)
        return '\n\n'.join([rows,cols])


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('matrix', type=str)
    args = parser.parse_args()

    print Matrix(args.matrix)

if __name__ == '__main__':
    main()

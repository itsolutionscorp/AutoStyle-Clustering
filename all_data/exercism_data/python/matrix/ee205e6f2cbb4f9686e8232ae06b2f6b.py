#!/usr/bin/python3


def make_ints(lst):
    lst_new = lst[:]
    for i, elt in enumerate(lst_new):
        lst_new[i] = int(elt)
    return lst_new


class Matrix:
    def __init__(self, str_):
        mtx_rows = str_.split('\n')
        mtx = [row.split() for row in mtx_rows]
        self._mtx = [make_ints(row) for row in mtx]
        self._trans_mtx = []
        for col_num, elt in enumerate(self._mtx[0]):
            self._trans_mtx.append([row[col_num] for row in self._mtx])

    @property
    def rows(self):
        return self._mtx

    @property
    def columns(self):
        return self._trans_mtx


if __name__ == '__main__':
    matrix = Matrix("1 2 3\n4 5 6\n7 8 9\n8 7 6")
    print(matrix.rows)
    print(matrix.columns[1])

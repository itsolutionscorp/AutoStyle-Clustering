__author__ = 'Adrian Rivera'


def extend_list_on_map(map, key, val):
    if not key in map:
        map[key] = [val]
    else:
        map[key].append(val)


class Matrix(object):

    def __init__(self, str_matrix):
        self.str_matrix = str_matrix
        self.rows = {}
        self.columns = {}
        self.row = 0
        self.col = 0
        self.value = ''
        self.parse()

    def parse(self):
        n = len(self.str_matrix)
        k = 0
        while k < n:
            ch = self.str_matrix[k]
            if ch == '\n':
                if not self.empty_condition():
                    self._report()
                    self._adjust_row()
            elif ch == ' ':
                if not self.empty_condition():
                    self._report()
                    self._adjust_col()
            else:
                self.value += ch
            k += 1
        self._report()

    def _adjust_row(self):
        self.row += 1
        self.col = 0
        self.value = ''

    def _adjust_col(self):
        self.col += 1
        self.value = ''

    def empty_condition(self):
        return not self.value.strip()

    def _report(self):
        el = int(self.value)
        extend_list_on_map(self.rows, self.row, el)
        extend_list_on_map(self.columns, self.col, el)

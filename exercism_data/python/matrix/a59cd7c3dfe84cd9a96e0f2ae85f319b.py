def split_and_parse_integers(s):
    return [int(c) for c in s.split(' ')]

class Matrix():

    def __init__(self, string):
        string_rows = string.split('\n')
        self.rows = [[int(n) for n in c.split(' ')] for c in string_rows]

    @property
    def columns(self):
        return [list(x) for x in list(zip(*self.rows))]

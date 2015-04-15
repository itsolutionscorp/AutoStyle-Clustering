class Matrix():

    def __init__(self, string):

        def split_and_parse_integers(s):
            return [int(c) for c in s.split(' ')]

        self.rows = [split_and_parse_integers(r) for r in string.split('\n')]

    @property
    def columns(self):
        return [list(x) for x in list(zip(*self.rows))]

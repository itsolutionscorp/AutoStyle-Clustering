class Matrix(object):
    def __init__(self, strrep):
        self.rows = []
        for rowdata in strrep.splitlines():
            self.rows.append([int(numstr) for numstr in rowdata.split()])
        self.columns = [list(ctuple) for ctuple in zip(*self.rows)]

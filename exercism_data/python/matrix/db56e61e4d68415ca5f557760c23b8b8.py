'''exer matrix'''


class Matrix(object):
    '''hold representation of matrix'''
    def __init__(self, mdata=''):
        '''init object using mdata which is rows marked by newlines and
        columns by spaces'''
        self.rows = []
        for row in mdata.split('\n'):
            self.rows.append([int(col) for col in row.split()])

        self.columns = []
        for col in self.rows[0]:        # use row0 to setup column structure
            self.columns.append([col])
        for row in self.rows[1:]:       # append remaining data into structure
            for ndx, col in enumerate(row):
                self.columns[ndx].append(col)

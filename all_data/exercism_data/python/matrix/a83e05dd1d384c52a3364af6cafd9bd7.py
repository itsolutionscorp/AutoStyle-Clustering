class Matrix(object):
    def __init__(self, matrix):
        self.matrix = matrix
        
        # generate rows as list of lists
        self.rows = []
        # first split on newline
        for item in self.matrix.split('\n'):
            # then split on space and convert string to int
            self.rows.append(map(int, item.split(' ')))

        # generate columns as list of lists
        # use '*' (splat) operator to unpack the argument list
        self.columns = [list (tuple) for tuple in zip(*self.rows)]

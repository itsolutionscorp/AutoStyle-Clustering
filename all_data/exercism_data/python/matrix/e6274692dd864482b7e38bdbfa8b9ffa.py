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
        self.columns = []
        # loop through the length of a row
        for i in range(len(self.rows[0])):
            # use list comprehenjsion to grab the i-th element of each row
            # and append to a new list
            self.columns.append([item[i] for item in self.rows])

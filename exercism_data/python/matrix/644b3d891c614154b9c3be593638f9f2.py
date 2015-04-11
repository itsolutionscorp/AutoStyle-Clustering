class Matrix():

    def __init__(self, mat_input):

        if mat_input:
            self.__mat = [row.strip().split(' ') for row in mat_input.split('\n')]
            self.rows = [[int(x) for x in row] for row in self.__mat]
            self.columns = zip(*self.rows)
        else:
            self.rows = None
            self.columns = None

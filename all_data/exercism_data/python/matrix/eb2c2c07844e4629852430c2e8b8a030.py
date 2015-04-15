class Matrix():

    def __init__(self, mat_input):
        
        if mat_input:
            self.__mat = [row.strip().split(' ') for row in mat_input.split('\n')]
            self.rows = [[int(x) for x in row] for row in self.__mat]
            self.columns = [[row[col] for row in self.rows]
                            for col in range(len(self.rows[0]))]
        else:
            self.rows = None
            self.columns = None

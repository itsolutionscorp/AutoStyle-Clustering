class Matrix():

    def __init__(self, mat_input):

        self.mat = [row.strip().split(' ') for row in mat_input.split('\n')]
        self.rows = [[int(x) for x in row] for row in self.mat]
        self.columns = [[row[col] for row in self.rows]
                        for col in range(len(self.rows[0]))]

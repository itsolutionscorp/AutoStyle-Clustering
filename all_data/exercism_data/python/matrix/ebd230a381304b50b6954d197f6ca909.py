import numpy as np
import string

class Matrix:

    def __init__(self, input_s):
        self.parse(input_s)
        self.rows = self.matrix.tolist()
        self.columns = self.matrix.T.tolist()

    def parse(self, s):
        parsed = [string.split(row, ' ') for row in string.split(s, '\n')]
        self.matrix = np.array(parsed, dtype = 'int')

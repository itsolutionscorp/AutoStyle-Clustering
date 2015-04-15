from numpy import *

class Matrix:

    def __init__(self, string):
        self.arr = array(map(lambda x: [int(y) for y in x], self.convert_to_2d_list(string) ))
        self.rows = self.arr.tolist()
        self.columns = self.arr.T.tolist()

    def convert_to_2d_list(self, string):
        return map(lambda y: y.split(' '), string.split('\n'))

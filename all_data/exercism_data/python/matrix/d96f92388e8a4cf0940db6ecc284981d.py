#!/usr/bin/env python3

class Matrix(object):
    def __init__(self, string):
        self.rows = [ [ int(item) for item in line.split() ] for line in string.split('\n') ]
        self.columns = [ list(col) for col in zip(*self.rows) ]

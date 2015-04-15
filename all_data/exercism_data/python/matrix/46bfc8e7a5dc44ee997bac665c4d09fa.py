#!/usr/bin/env python2
class Matrix:
    def __init__(self, string):
        self.rows = [map(int, i.split()) for i in string.split("\n")]
        self.columns = [[j[i] for j in self.rows] for i in range(len(self.rows[0]))]

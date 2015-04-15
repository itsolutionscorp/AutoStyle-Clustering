#!/usr/bin/python

from itertools import izip

class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence

    def hamming_distance(self, sequence):
        combined = izip(sequence, self.sequence)
        f = lambda x: x[0] != x[1]
        return sum(map(f, combined))

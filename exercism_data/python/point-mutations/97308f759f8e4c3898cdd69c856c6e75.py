# -*- coding: utf-8 -*-
from itertools import izip


class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence.upper()

    def __sub__(self, other):
        return sum(na1 != nb1 for (na1, nb1) in izip(self.sequence, other.sequence))

    def hamming_distance(self, sequence):
        return self - DNA(sequence)

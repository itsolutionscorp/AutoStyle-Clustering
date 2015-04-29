# -*- coding: utf-8 -*-


class DNA(object):
    def __init__(self, sequence):
        self.sequence = list(sequence.upper())

    def __sub__(self, other):
        return sum((1 for (na1, nb1) in zip(self.sequence, other.sequence) if na1 != nb1))

    def hamming_distance(self, sequence):
        return self - DNA(sequence)

# -*- coding: utf-8 -*-


class DNA(object):
    def __init__(self, sequence):
        self.sequence = list(sequence.upper())

    def __sub__(self, other):
        diff = 0
        for i in range(0, len(self.sequence)):
            try:
                diff += 0 if self.sequence[i] == other.sequence[i] else 1
            except IndexError:
                pass
        return diff

    def hamming_distance(self, sequence):
        return self - DNA(sequence)

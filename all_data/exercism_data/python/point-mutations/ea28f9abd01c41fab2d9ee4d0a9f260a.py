# -*- coding:utf-8 -*-

class DNA(object):
    def __init__(self, strand):
        self._strand = strand
        
    def hamming_distance(self, strand):
        return sum(a != b for a, b in zip(self._strand, strand))

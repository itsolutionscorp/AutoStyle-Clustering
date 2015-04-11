#!/usr/bin/env python

class DNA:
    def __init__(self, sequence):
        self.sequence = sequence
    
    def hamming_distance(self, other_sequence):
        distance = 0
        for n1, n2 in zip(self.sequence, other_sequence):
            if n1 != n2:
                distance += 1
        return distance

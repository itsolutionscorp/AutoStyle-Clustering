#!/usr/bin/env python3

class DNA(str):
    def hamming_distance(self, other):
        return sum(left != right for (left, right) in zip(self, other))

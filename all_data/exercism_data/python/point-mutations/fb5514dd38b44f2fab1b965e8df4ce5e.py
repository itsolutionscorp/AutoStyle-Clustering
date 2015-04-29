#!/usr/bin/env python3

class DNA(str):
    def hamming_distance(self, other):
        return sum(1 for (left, right) in zip(self, other) if left != right)

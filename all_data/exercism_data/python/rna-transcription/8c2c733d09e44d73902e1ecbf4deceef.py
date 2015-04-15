#!/usr/bin/env python
# -*- coding: utf-8


class DNA(object):
    COMPLEMENTS = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"
    }

    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        rna = [self.COMPLEMENTS.get(symbol) for symbol in self.sequence]
        assert all(rna), "Invalid input sequence"
        return "".join(rna)

if __name__ == "__main__":
    pass

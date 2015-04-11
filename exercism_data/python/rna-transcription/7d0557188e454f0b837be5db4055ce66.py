#!/usr/bin/python

class DNA:
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        result = ""
        for c in self.dna:
            if c == 'G': result += "C"
            elif c == 'C': result += "G"
            elif c == 'T': result += "A"
            elif c == 'A': result += "U"
        return result

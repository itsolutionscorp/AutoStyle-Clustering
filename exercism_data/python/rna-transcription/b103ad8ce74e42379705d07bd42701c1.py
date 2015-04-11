#!/usr/bin/env python3

class DNA(object):
    mapping = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def translator(self):
        for c in self.nucleotides:
            yield DNA.mapping[c]

    def to_rna(self):
        return ''.join(self.translator())

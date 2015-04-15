#!/usr/bin/env python

class DNA:
    def __init__(self, n):
        self.strand = n;
    def to_rna(self):
        rna = ''
        for i in xrange(0, len(self.strand)):
            if self.strand[i] == 'C':
                rna += 'G'
            elif self.strand[i] == 'G':
                rna += 'C'
            elif self.strand[i] == 'T':
                rna += 'A'
            elif self.strand[i] == 'A':
                rna += 'U'
        return rna

if __name__ == '__main__':
    x = raw_input('Enter a DNA strand to transcribe.\n')
    print DNA(x).to_rna()

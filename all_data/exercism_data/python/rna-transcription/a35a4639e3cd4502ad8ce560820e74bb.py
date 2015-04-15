# -*- coding: utf-8 -*-

class DNA:

    def __init__(self, strand: str):
        self.strand = strand

    def to_rna(self) -> str:
        return "".join(self._to_rna(n) for n in self.strand)

    def _to_rna(self, nucleotide: str) -> str:
        if nucleotide == 'G':
            return 'C'
        elif nucleotide == 'C':
            return 'G'
        elif nucleotide == 'T':
            return 'A'
        elif nucleotide == 'A':
            return 'U'
        else:
            raise Exception("invalid nucleotide '{}'".format(nucleotide))

#!/usr/bin/env python
# vim: set fileencoding=utf-8 :


class DNA(object):
    """DNA strands are a sequence of nucleotides

    The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
    guanine (**G**) and thymidine (**T**).
    """

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        """Take a DNA strand and return its transcribed RNA strand

        Given a DNA strand, its transcribed RNA strand is formed by replacing
        all occurrences of thymidine (**T**) with uracil (**U**).
        """

        self.strand = self.strand.replace('T', 'U')
        return self.strand

#!/usr/bin/python
# -*- coding: utf-8 -*-

class DNA(object):
    """
    This class provides methods related to a DNA string which is passes as
    constructor parameter.

    The method 'to_rna' translates the DNA string to the corresponding
    transcribed RNA string.
    """

    def __init__(self, dna_strand):
        self._dna_strand = dna_strand

    def to_rna(self):
        return self._dna_strand.replace('T', 'U')

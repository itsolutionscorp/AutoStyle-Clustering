#!/usr/bin/python

from string import maketrans

def to_rna(dna):
    indna= "GCTA"
    outrna= "CGAU"
    trandna= maketrans(indna, outrna)

    return dna.translate(trandna)

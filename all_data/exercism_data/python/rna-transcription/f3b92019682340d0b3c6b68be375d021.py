#!/usr/bin/env python

def to_rna(dna):
    return dna.translate( str.maketrans("GCTA", "CGAU") )

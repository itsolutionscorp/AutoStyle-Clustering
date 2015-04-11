#!/usr/bin/env python3

def to_rna(dna : str, rule=str.maketrans("GCTA","CGAU")):
    return dna.upper().translate(rule)

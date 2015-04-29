#!/usr/bin/env python

def to_rna(dna):
    rna = []
    for char in dna:
        if char == "G":
            rna.append("C")
        elif char == "C":
            rna.append("G")
        elif char == "T":
            rna.append("A")
        elif char == "A":
            rna.append("U")
    return "".join(rna)

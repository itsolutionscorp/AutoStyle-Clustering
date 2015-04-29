#!/usr/bin/python
import string
def to_rna(sequence):
    sequence=sequence.upper()
    trans=string.maketrans("GCTA","CGAU")
    return sequence.translate(trans)

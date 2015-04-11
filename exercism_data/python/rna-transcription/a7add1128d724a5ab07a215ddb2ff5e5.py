__author__ = 'colinc'


def to_rna(dna):
    mapping = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"
    }
    return "".join(mapping[j] for j in dna)

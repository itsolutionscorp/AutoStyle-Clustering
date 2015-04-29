__author__ = 'romleinster'


def to_rna(dna):
    rna = ""
    translation = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    for letter in dna:
        if letter in translation.keys():
            rna += translation[letter]
    return rna

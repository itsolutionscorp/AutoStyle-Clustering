#!/usr/bin/python3.4


def to_rna(dna):
    rna=''
    rna_trans = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    for nucleotide in dna:
        rna += rna_trans[nucleotide]
    return rna

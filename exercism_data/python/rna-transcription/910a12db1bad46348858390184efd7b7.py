__author__ = 'Cedric Zhuang'

dna2rna = {'G': 'C',
           'C': 'G',
           'T': 'A',
           'A': 'U'}


def to_rna(dna):
    byte_array = bytearray(dna)
    for i, v in enumerate(dna):
        byte_array[i] = dna2rna[v]
    return str(byte_array)

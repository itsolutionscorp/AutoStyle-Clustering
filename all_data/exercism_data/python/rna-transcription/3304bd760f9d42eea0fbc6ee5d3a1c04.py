
from string import maketrans, translate


def to_rna(s):
    dna, rna = 'GCTA', 'CGAU'
    dna_table = maketrans(dna, rna)
    return translate(s, dna_table)

__author__ = 'sagos'


def to_rna(dna):
    rna1 = {'C': 'X', 'G': 'Y', 'T': 'A', 'A': 'U'}
    rna2 = {'X': 'G', 'Y': 'C'}
    for i, j in rna1.iteritems():
        dna = dna.replace(i, j)

    for i, j in rna2.iteritems():
        dna = dna.replace(i, j)

    return dna

NUCLEOTIDES = {'DNA': 'GCTA', 'RNA': 'CGAU'}


def to_rna(dna):
    mapping = str.maketrans(NUCLEOTIDES['DNA'], NUCLEOTIDES['RNA'])
    return dna.translate(mapping)

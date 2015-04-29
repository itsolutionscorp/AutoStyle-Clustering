from string import maketrans

dna2rna = maketrans('GCTA', 'CGAU')


def to_rna(nucleotides):
    return nucleotides.translate(dna2rna)

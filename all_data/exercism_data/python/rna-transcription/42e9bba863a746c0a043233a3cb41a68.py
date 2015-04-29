from string import maketrans


def to_rna(strand):
    dna = "GCTA"
    rna = "CGAU"

    return strand.translate(maketrans(dna, rna))

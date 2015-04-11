from string import maketrans


def to_rna(strand):
    return strand.translate(maketrans("GCTA","CGAU"))

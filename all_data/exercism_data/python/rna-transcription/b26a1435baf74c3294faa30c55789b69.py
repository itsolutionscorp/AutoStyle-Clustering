from string import maketrans

trans_to_rna = maketrans("GCTA","CGAU")


def to_rna(strand):
    return strand.translate(trans_to_rna)

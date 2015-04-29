import string


def to_rna(seq):
    dna_to_rna_trans = string.maketrans('GCTA','CGAU')
    return seq.translate(dna_to_rna_trans)

import string

def to_rna(sequence):
    return sequence.translate(string.maketrans("GCTA","CGAU"))

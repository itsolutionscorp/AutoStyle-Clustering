import string

def to_rna(dna):
    return string.translate(dna, string.maketrans("GCTA","CGAU"))

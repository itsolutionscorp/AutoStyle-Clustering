import string

def to_rna(s):
    return s.translate(string.maketrans("GCTA", "CGAU"))

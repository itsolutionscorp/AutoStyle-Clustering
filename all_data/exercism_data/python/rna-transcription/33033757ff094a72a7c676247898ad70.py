import string

def to_rna(s):
    return s.translate(s.maketrans("CGAT", "GCUA"))

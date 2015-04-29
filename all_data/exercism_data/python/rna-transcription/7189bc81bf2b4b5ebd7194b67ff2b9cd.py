import string

def to_rna(DNA):
    return DNA.translate(string.maketrans('GCTA','CGAU'))

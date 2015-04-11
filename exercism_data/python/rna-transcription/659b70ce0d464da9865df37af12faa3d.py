def to_rna(s):
    return s.translate(str.maketrans('GCTA','CGAU'))

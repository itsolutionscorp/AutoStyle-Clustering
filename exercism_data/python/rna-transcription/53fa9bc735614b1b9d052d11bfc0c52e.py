import string

def to_rna(S):
    return S.translate(string.maketrans('ACGT', 'UGCA'))

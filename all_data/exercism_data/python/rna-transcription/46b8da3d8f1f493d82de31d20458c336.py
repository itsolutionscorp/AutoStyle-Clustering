import string

def to_rna(input):
    result=input.translate(string.maketrans('GCTA', 'CGAU'))
    return result

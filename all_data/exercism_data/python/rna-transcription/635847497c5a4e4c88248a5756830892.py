__author__ = 'Hinek'

def to_rna(str):
    result = ''
    for s in str:
        if s == 'C':
            result = result + 'G'
        elif s == 'G':
            result = result + 'C'
        elif s == 'T':
            result = result + 'A'
        elif s == 'A':
            result = result + 'U'
    return result

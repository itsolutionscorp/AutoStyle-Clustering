""" Translate input DNA string to RNA
"""
def to_rna(s):
    rna = ''
    for n in s:
        rna += transcribe(n)
    return rna

""" Helper funcion to to_rna. Returns appropriate
    complement for given nucleotide string s
"""
def transcribe(s):
    if s == 'G':
        return 'C'
    elif s == 'C':
        return 'G'
    elif s == 'T':
        return 'A'
    elif s == 'A':
        return'U'
    else:
        print 'ERROR - invalid nucleotide!'
        return s

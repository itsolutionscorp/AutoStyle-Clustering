__author__ = 'nebur1989'

def to_rna(dna):
    rna = ""
    for c in dna:
        if c == 'G':
            rna += 'C'
        elif c == 'C':
            rna += 'G'
        elif c == 'T':
            rna += 'A'
        elif c == 'A':
            rna += 'U'
    return rna

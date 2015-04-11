"""
G -> C
C -> G
T -> A
A -> U
"""

def to_rna(strand):
    rna = ''
    for letter in strand:
        if letter == 'C':
            rna += 'G'
        elif letter == 'G':
            rna += 'C'
        elif letter == 'T':
            rna += 'A'
        elif letter == 'A':
            rna += 'U'
    return rna

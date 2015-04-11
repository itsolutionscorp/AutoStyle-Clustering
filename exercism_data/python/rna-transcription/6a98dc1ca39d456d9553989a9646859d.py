# dna.py
# `G` -> `C`
# `C` -> `G`
# `T` -> `A`
# `A` -> `U`

def to_rna(dna):
    rna = ''
    for nuc in dna:
        if nuc == 'G':
            add = 'C'
        elif nuc == 'C':
            add = 'G'
        elif nuc == 'T':
            add = 'A'
        elif nuc == 'A':
            add = 'U'
        rna += add
    return rna

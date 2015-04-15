__author__ = 'citypulse-dp'


# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

def to_rna(dna):
    rna = ''
    transcript = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    for nucleotid in dna:
        rna += transcript[nucleotid]
    return rna

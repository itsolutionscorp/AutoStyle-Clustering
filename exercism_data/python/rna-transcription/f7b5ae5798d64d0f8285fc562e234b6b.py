"""
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

def to_rna(dna):
    rna = []
    for nucleotide in dna:
        if nucleotide == 'G':
            rna.append('C')
        if nucleotide == 'C':
            rna.append('G')
        if nucleotide == 'T':
            rna.append('A')
        if nucleotide == 'A':
            rna.append('U')
    return "".join(rna)

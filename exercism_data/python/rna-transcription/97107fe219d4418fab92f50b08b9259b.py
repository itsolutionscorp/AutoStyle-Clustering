#
# Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).
#
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
#
#

def to_rna(dna):

    dna_nuc = list(dna)

    rna_nuc = ['G' if x == 'C' else ('C' if x == 'G' else ('A' if x == 'T' else ('U' if x == 'A' else x))) for x in dna_nuc]

    rna = ''.join(rna_nuc)

    return rna

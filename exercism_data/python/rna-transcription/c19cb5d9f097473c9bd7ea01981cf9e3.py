def replace_nucleotide(nucleotide):
    """ transcribe base pairs in DNA to create RNA """
    # `G` -> `C`
    # `C` -> `G`
    # `T` -> `A`
    # `A` -> `U`
    pairs = {'G': "C", 'C': "G", 'T': "A", 'A': "U"}
    return pairs[nucleotide]

def to_rna(strand):
    """ provide string of RNA transcribed nucleotides in DNA strand"""
    return ''.join(map(replace_nucleotide, list(strand)))

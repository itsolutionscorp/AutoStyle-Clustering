def replace_nucleotide(nucleotide):
    """ transcribe base pairs in DNA to create RNA """
    # `G` -> `C`
    # `C` -> `G`
    # `T` -> `A`
    # `A` -> `U`
    pairs = {'G': "C", 'C': "G", 'T': "A", 'A': "U"}
    return pairs[nucleotide]

def translate_nucleotides(strand):
    import string
    #take a whack at string.translate
    pairs = string.maketrans("GCTA", "CGAU")
    return strand.translate(pairs)

def to_rna(strand):
    """ provide string of RNA transcribed nucleotides in DNA strand"""
    return translate_nucleotides(strand)

def to_rna(strand):
    """ provide string of RNA transcribed nucleotides in DNA strand"""
    # `G` -> `C`
    # `C` -> `G`
    # `T` -> `A`
    # `A` -> `U`
    import string
    return strand.translate(string.maketrans("GCTA", "CGAU"))

def complement(nucleotide):
    if nucleotide == "G":
        return "C"
    elif nucleotide == "C":
        return "G"
    elif nucleotide == "T":
        return "A"
    elif nucleotide == "A":
        return "U"

def to_rna(dna):
    """
    Returns the RNA complement of a DNA strand.
    """
    return "".join(map(complement, dna))

def to_rna(dna):
    """
    Returns the RNA complement of a DNA strand.
    """
    translation = str.maketrans("GCTA", "CGAU")
    return dna.translate(translation)

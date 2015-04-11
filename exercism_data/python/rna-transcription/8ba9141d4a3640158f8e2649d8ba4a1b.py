def to_rna(dna):
    """Given a DNA strand return its RNA complement."""

    dna_to_rna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    rna = []
    for nuc in dna:
        rna.append(dna_to_rna_dict[nuc])

    return ''.join(rna)

def to_rna(strand):
    """Returns the RNA compliment of a DNA strand.

    Args:
        strand: A string representation of a DNA strand.

    Returns:
        A string representation of the RNA compliment of the DNA
        strand entered as an argument to the function.
    """
    rna_list = []

    for ntide in strand:
        if ntide == 'G':
            rna_list.append('C')
        elif ntide == 'C':
            rna_list.append('G')
        elif ntide == 'T':
            rna_list.append('A')
        elif ntide == 'A':
            rna_list.append('U')

    rna = ''.join(rna_list)

    return rna

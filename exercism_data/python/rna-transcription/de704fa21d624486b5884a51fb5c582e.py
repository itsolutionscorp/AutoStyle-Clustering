__author__ = 'grdunn'


def to_rna(dna):
    """

    given a DNA strand, returns its RNA complement (per RNA transcription).

    :param dna:
    :rtype : str
    """

    complement = {'T':'A', 'A':'U', 'G':'C', 'C':'G'}

    rna = []

    for base in dna:
        rna.append(complement[base])

    return ''.join(rna)

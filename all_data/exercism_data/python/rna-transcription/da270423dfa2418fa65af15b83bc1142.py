from string import maketrans


def to_rna(seq):
    """
    Convert DNA nucleotides to their RNA equivalents.
    """
    return seq.translate(maketrans('GCTA', 'CGAU'))

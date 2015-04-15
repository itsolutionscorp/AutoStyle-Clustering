"""
dna

"""


def to_rna(dna):
    """
    Return the RNA complement of a DNA strand

    :param dna:
    :return:
    """
    # We'll keep our nucleotide complements
    # in a dictionary for quick lookup
    nucleotide_map = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    # Now just replace each nucleotide in DNA with
    # its complement.
    # Python strings are immutable, so we'll create
    # a list to join it to return a string.
    return ''.join(
        [ nucleotide_map[n] for n in dna ]
    )

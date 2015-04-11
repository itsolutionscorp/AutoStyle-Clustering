__author__ = 'James'


def to_rna(dna):
    """
    Translates nucleotides in given dna sequence.
    :param dna: String representation of a dna sequence
    :return: String representation of an rna sequence.
    """

    # A dictionary for listing how sequences should be translated. Line breaks for readability.
    trans = {'G': 'C',
             'C': 'G',
             'T': 'A',
             'A': 'U'}

    return ''.join(trans[nuc] for nuc in dna)  # List comprehension to translate based on trans dictionary.

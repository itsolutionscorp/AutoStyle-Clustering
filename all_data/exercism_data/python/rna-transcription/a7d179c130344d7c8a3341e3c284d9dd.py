translator = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(dna):
    """Converts a string that represents a DNA to RNA

    :dna: a string object
    :returns: a string object

    """
    return ''.join([translator[nucleotide] for nucleotide in dna])

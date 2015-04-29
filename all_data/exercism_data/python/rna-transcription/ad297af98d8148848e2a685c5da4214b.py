translator = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(dna):
    """TODO: Docstring for to_rna.

    :dna: TODO
    :returns: TODO

    """
    return ''.join([translator[nucleotide] for nucleotide in dna])

DNA_TO_RNA = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}


def to_rna(nucleotide):
    """
    Returns the transcribed RNA nucleotide sequence for a given DNA
    nucleotide sequence.
    """
    return ''.join([DNA_TO_RNA[n] for n in nucleotide])

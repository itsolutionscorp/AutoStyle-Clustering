def to_rna(dna):
    """Transcribe DNA to RNA"""

    translation = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    rna = ''.join([translation[strand] for strand in dna])

    return rna

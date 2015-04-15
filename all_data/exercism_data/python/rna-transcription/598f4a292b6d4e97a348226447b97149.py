CODE = {'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'}


def to_rna(dna):
    """Transcribe a dna strand to an rna strand."""
    rna_it = (CODE[strand] for strand in dna)
    return ''.join(rna_it)

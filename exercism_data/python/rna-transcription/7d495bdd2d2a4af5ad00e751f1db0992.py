"""exercism.io - Python 'RNA Transcription' exercise solution."""

def to_rna(dna_str):

    transcription_dict = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    rna_str = ''
    for nucleotide in dna_str:
        rna_str += transcription_dict[nucleotide]

    return rna_str

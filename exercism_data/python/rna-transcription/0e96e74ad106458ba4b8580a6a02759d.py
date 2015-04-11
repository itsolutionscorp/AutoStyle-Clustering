"""exercism.io - Python 'RNA Transcription' exercise solution."""
from string import maketrans, translate

def to_rna(dna_str):
    return dna_str.translate(maketrans('GCTA', 'CGAU'))

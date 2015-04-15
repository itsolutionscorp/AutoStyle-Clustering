"""exercism.io - Python 'RNA Transcription' exercise solution."""
from string import maketrans, translate

dna2rna_map = maketrans('GCTA', 'CGAU')

def to_rna(dna_str):
    return dna_str.translate(dna2rna_map)

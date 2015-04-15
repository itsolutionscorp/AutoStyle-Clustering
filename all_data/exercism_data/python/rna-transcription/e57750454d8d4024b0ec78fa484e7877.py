"""
Exercise 4: RNA transcription
"""
from string import maketrans

trans = maketrans('GCTA', 'CGAU')

def to_rna(dna):
    """
    Make a translation pattern, translate the dna.
    """
    return dna.translate(trans)

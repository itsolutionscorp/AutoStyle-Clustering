"""
Exercise 4: RNA transcription
"""
from string import maketrans

def to_rna(dna):
    """
    Make a translation pattern, translate the dna.
    """
    trans = maketrans('GCTA', 'CGAU')
    return dna.translate(trans)

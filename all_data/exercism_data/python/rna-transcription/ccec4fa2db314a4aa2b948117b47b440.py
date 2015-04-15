import string

_translation_table = string.maketrans('GCTA', 'CGAU')

def to_rna(dna):
    return dna.translate(_translation_table)

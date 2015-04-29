# dna.py
# exercism.io: Python Exercise #4
from string import maketrans


def to_rna(dna):
    """Returns the matching RNA strand."""

    return dna.translate(maketrans('GCTA', 'CGAU'))

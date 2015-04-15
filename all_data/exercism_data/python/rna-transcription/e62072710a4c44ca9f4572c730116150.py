__author__ = 'octowl'
from string import maketrans

transcript = maketrans("GCTA", "CGAU")


def to_rna(dna):
    return dna.translate(transcript)

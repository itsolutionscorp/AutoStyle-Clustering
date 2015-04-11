__author__ = 'js'

from string import maketrans

def to_rna(sequence):
    # DNA strand
    nucleotide_in = 'GCTA'
    # RNA transcription
    nucleotide_out = 'CGAU'
    # Creates translation table for str.translate
    translated = maketrans(nucleotide_in, nucleotide_out)
    # Returns the string, replacing characters according to the table
    return sequence.translate(translated)

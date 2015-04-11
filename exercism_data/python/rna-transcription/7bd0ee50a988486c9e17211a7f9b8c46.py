# coding=utf-8
__author__ = 'jimblackler'


def to_rna(strand):
    """Given a DNA strand, returns its RNA complement (per RNA transcription).

    :param strand: The DNA strand.
    :return: The RNA complement.
    """
    transforms = {'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U'}
    return ''.join(map(lambda c: transforms[c], list(strand)))

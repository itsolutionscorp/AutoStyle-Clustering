# -*- coding: utf-8 -*-
import re


def to_rna(dna):
    """ given a DNA strand, returns its RNA complement (per RNA transcription)

    :param dna: DNA strand
    :type dna: str
    :return: thr RNA complement of the given DNA strand
    :rtype: str
    """
    dna = dna.upper()
    if re.match('^[GCTA]+$', dna) is not None:
        rna = ''
        for nucleotide in dna:
            rna += replace_dna(nucleotide)
        return rna
    else:
        raise ValueError("DNA strand not valid")


def replace_dna(nucleotide):
    """ Replaces a single DNA nucleotide to a RNA nucleotide

    :param nucleotide: the DNA nucleotide to change
    :type nucleotide: str
    :return: RNA nucleotide
    :rtype: str
    """
    replace_rules = [
        ('G', 'C'),
        ('C', 'G'),
        ('T', 'A'),
        ('A', 'U')
    ]
    for adn_n, arn_n in replace_rules:
        temp = nucleotide.replace(adn_n, arn_n)
        #exit upon the first replace, this prevents 'G' getting changed to 'C'
        # and then 'C' replaced again for 'G'
        if temp != nucleotide: return temp

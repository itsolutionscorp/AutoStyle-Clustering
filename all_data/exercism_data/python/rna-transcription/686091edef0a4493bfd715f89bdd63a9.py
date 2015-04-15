# -*- coding: utf-8 -*-


def to_rna(seq):
    """
    Takes a DNA sequence and transforms it into a RNA sequence.
    """
    seq=seq.replace('A','U')
    seq=seq.replace('T','A')
    seq=seq.replace('C',"P")
    seq=seq.replace('G','C')
    seq=seq.replace('P','G')
    return seq

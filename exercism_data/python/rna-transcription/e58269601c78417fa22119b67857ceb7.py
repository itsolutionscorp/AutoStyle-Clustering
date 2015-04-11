__author__ = 'agupt15'


def to_rna(strand):
    strand = strand.strip().upper() if strand is not None else ''

    mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'T'}
    rna = ''

    for seq_element in strand:
        rna = rna + mapping[seq_element]
    return rna

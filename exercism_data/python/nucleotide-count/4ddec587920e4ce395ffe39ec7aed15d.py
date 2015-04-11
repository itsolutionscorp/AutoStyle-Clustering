#!/usr/bin/python


def count(sequence, nucleotide):
    if nucleotide not in "ACTGU":
        return len(filter(lambda x: x == nucleotide, list(sequence)))
    else:
        raise ValueError(str(nucleotide)+' is not an allowed symbol.')


def nucleotide_counts(sequence):
    counts_table = {'A': 0, 'C': 0, 'T': 0, 'G': 0}
    for nucleotide in list(sequence):
        if nucleotide in "ACTG":
            counts_table[nucleotide] += 1
        elif nucleotide == U:
            pass
        else:
            raise ValueError(str(nucleotide)+' is not an allowed symbol.')
    return counts_table

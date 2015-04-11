#!/usr/bin/python


def count(sequence, nucleotide):
    if nucleotide == 'A' or nucleotide == 'C' or nucleotide == 'G' or nucleotide == 'T'\
            or nucleotide == 'U':
        return len(filter(lambda x: x == nucleotide, list(sequence)))
    else:
        raise ValueError(str(nucleotide)+' is not an allowed symbol.')


def nucleotide_counts(sequence):
    counts_table = {'A': 0, 'C': 0, 'T': 0, 'G': 0}
    for nucleotide in list(sequence):
        if nucleotide == 'A' or nucleotide == 'C' or nucleotide == 'G' or nucleotide == 'T':
            counts_table[nucleotide] += 1
        else:
            raise ValueError(str(nucleotide)+' is not an allowed symbol.')
    return counts_table

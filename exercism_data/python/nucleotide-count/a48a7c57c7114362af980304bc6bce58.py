#!/usr/bin/python3


def count(sequence, nucleotide):
    """
    Returns integer - how many times nucleotide occurs in sequence.
    Raises ValueError if nucleotide or sequence is wrong.
    """
    # verify nucleotide
    if nucleotide.upper() not in list('ACGT'):
        raise ValueError('Wrong nucleotide symbol')

    # verify sequence - this function raises ValueError if seq is wrong
    nucleotide_counts(sequence)

    return sequence.upper().count(nucleotide.upper())


def nucleotide_counts(sequence):
    """
    Returns dictionary with counts for every nucleotide in sequence.
    Raises ValueError if sequence is wrongly composed.
    """
    counts = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
    try:
        for nucleotide in sequence.upper():
            counts[nucleotide] += 1
    except KeyError:
        raise ValueError('Sequence contains other than ATCG nucleotides')
    return counts


if __name__ == "__main__":
    print(count('aaaaaattttccccgggx', 'ta'))

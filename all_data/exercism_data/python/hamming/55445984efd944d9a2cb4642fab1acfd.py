__author__ = 'agupt15'


def distance(strand1, strand2):
    strand1 = strand1.strip().upper() if strand1 is not None else ''
    strand2 = strand2.strip().upper() if strand2 is not None else ''

    hamming_diff = 0

    if len(strand1) != len(strand2):
        return hamming_diff

    for index in range(len(strand1)):
        if strand1[index] != strand2[index]:
            hamming_diff += 1
    return hamming_diff

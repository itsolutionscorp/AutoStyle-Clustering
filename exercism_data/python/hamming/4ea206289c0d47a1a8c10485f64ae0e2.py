import itertools


def hamming(strand_one, strand_two):
    return len([x for x, y in itertools.zip_longest(strand_one, strand_two) if x != y])

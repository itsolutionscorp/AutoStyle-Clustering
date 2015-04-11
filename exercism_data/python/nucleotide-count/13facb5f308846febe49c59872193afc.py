from collections import Counter

def count(haystack, needle):
    if needle not in "ACGTU":
        raise ValueError('{} is not a nucleotide'.format(needle))
    return haystack.count(needle)

def nucleotide_counts(s):
    c = Counter({'A': 0, 'C': 0, 'T': 0, 'G': 0})
    c.update(s)
    return c

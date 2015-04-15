from collections import Counter


VALID_NUCLEOTIDES = 'ATCG'


def nucleotide_counts(dna_string):
    if any(nucleotide not in VALID_NUCLEOTIDES for nucleotide in dna_string):
        raise ValueError

    counts = Counter(dna_string)

    # This is stupid, but necessary the way the tests are written.
    for nucleotide in VALID_NUCLEOTIDES:
        if counts[nucleotide] == 0:
            counts[nucleotide] = 0

    return dict(counts)


def count(dna_string, nucleotide):
    # Also stupid.
    if nucleotide == 'U':
        return 0

    if nucleotide not in VALID_NUCLEOTIDES:
        raise ValueError

    return nucleotide_counts(dna_string)[nucleotide]

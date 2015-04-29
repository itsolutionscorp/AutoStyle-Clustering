# vim:fileencoding=utf-8


NUCLEOTIDES = ('A', 'C', 'G', 'T')
NUCLEOTIDES_SET = set(NUCLEOTIDES)


def count(dna, nucleotide):
    if nucleotide not in NUCLEOTIDES:
        raise ValueError
    return dna.count(nucleotide)


def nucleotide_counts(dna):
    return dict([
        (nucleotide, count(dna, nucleotide))
        for nucleotide in ('A', 'C', 'G', 'T')
    ])

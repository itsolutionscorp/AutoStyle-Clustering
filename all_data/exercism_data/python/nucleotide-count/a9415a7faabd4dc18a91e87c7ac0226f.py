__author__ = 'nebur1989'


def nucleotide_counts(dna):
    counts = {'A': 0, 'C': 0, 'G': 0, 'T': 0}
    for nucleotide in dna:
        if nucleotide in counts:
            counts[nucleotide] += 1
        else:
            raise ValueError
    return counts


def count(dna, nucleotide):
    counts = nucleotide_counts(dna)
    if nucleotide in counts:
        return counts[nucleotide]
    else:
        raise ValueError

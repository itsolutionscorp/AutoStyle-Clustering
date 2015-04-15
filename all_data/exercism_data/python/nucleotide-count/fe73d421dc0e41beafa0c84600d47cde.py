__author__ = 'oniwa'


def count(dna, nucleo_type):
    if nucleo_type is 'U':
        return 0
    try:
        nucleo_dict = nucleotide_counts(dna)
        return nucleo_dict[nucleo_type]
    except KeyError:
        raise ValueError("Not a nucleotide found in DNA")


def nucleotide_counts(dna):
    nucleotides = dna
    dna_dict = {'A': 0, 'C': 0, 'T': 0, 'G': 0}
    for item in nucleotides:
        dna_dict[item] += 1

    return dna_dict

complement = {'G': 'C',
              'C': 'G',
              'T': 'A',
              'A': 'U'}


def to_rna(dna):
    return ''.join(complement[nucleotide] for nucleotide in dna)

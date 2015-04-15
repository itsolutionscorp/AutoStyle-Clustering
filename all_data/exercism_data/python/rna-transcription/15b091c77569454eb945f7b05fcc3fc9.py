COMPLEMENT = {'G': 'C',
              'C': 'G',
              'T': 'A',
              'A': 'U'}


def to_rna(sequence):
    return ''.join([COMPLEMENT[nucleotide] for nucleotide in sequence])

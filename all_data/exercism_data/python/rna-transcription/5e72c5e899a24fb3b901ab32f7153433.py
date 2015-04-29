def to_rna(dna):
    mapper = {'G': 'C',
              'C': 'G',
              'T': 'A',
              'A': 'U'}

    return ''.join(map(lambda x: mapper[x], dna))

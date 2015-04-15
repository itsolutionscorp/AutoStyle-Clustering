def to_rna(sequence):
    mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    rna = map(lambda char: mapping.get(char), sequence)

    return(''.join(rna))

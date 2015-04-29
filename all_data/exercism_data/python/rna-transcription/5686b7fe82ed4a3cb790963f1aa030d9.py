def to_rna(sequence):
    mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    rna = map(mapping.get, sequence)

    return(''.join(rna))

def to_rna(seq):
    dna_map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    result = ''
    for letter in seq:
        result += dna_map[letter]
    return result

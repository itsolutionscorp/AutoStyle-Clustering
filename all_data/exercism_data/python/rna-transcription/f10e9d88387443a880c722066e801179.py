def to_rna(dna):
    conversions = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    l = list(dna)
    l = [conversions[letter] for letter in l]
    return ''.join(l)

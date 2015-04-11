def to_rna(dna):
    result = ''
    translations = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    for char in dna:
        result += translations[char]
    return result

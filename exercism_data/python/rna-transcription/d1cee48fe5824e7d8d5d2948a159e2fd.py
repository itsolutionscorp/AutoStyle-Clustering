def to_rna(dna):
    complements = {'G': 'C', 'C': 'G', 'A': 'U', 'T': 'A'}
    result = [complements[x] for x in dna]
    return ''.join(result)

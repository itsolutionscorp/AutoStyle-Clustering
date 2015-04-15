def to_rna(dna):
    rnaMap = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }
    return ''.join([rnaMap[c] for c in dna])

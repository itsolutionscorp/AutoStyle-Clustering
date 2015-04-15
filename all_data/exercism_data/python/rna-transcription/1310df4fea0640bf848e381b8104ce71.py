complements = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    return ''.join(complements[nucleotide] for nucleotide in dna)

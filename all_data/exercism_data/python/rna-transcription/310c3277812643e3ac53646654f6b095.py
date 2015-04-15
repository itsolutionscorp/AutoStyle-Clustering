DNA_TO_RNA = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    rna = ''
    for nucleotide in dna:
        rna += DNA_TO_RNA[nucleotide]
    return rna

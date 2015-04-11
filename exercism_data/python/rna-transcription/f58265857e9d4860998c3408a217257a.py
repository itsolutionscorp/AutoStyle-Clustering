DNA_TO_RNA_KEY = {'G': 'C',
              'C': 'G',
              'T': 'A',
              'A': 'U'}

def to_rna(dna):
    rna = ''
    for nucleotide in dna:
        rna += DNA_TO_RNA_KEY[nucleotide]
    return rna

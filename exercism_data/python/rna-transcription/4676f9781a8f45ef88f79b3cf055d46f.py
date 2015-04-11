transcription = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    rna = ''
    for x in dna:
        rna += transcription[x]
    return rna

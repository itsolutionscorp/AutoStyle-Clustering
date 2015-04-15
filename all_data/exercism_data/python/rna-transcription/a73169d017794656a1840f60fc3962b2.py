# Python Exercism #4 RNA Transcription

def to_rna(dna_strand):
    trans = {'G': 'C',
             'C': 'G',
       	     'T': 'A',
             'A': 'U'}

    dna = ''
    for char in dna_strand:
        dna += trans[char]
    return dna

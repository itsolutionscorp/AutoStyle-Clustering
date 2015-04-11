'''
dna.py

Generates RNA strands using transcription
'''

def to_rna(strand):
    compliment = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return ''.join([compliment[n] for n in strand])

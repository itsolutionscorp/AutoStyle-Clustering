transcription = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    return ''.join(transcription[x] for x in dna)

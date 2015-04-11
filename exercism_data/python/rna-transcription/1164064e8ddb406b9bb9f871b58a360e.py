def to_rna(dna):
    transcription = {'A':'U', 'C':'G', 'G':'C', 'T':'A' }
    return ''.join([transcription[c] for c in dna])

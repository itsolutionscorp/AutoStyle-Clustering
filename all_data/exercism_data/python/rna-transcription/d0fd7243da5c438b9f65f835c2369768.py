def to_rna(dna):
    transcription = {'A':'U', 'C':'G', 'G':'C', 'T':'A' }
    return ''.join([transcription[c] for c in dna])

#def transcribe(c):
    #if c == 'G':
        #return 'C'
    #elif c == 'A':
        #return 'U'
    #elif c == 'C':
        #return 'G'
    #elif c == 'T':
        #return 'A'

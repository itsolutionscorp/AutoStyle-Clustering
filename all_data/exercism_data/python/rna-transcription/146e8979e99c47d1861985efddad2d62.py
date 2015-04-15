##Transcribe a strand of DNA into a strand of RNA


def to_rna(dna):
    #transcription: DNA base is key, RNA transcript is value
    transcription = {'C':'G', 'G':'C', 'A':'U', 'T':'A'}
    #return RNA string after iterating through input DNA string
    return ''.join([transcription[base] for base in dna])

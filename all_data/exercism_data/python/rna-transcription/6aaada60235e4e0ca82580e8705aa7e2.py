def to_rna(strand):

    transcription={'C':'G','G':'C','A':'U','T':'A'}

    complementStrand=''.join([transcription[letter] for letter in strand])


    return complementStrand

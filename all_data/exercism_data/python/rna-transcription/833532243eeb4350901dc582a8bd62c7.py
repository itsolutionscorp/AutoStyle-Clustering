def to_rna(sequence):
    
    # Transcription dictionary.
    transscription_dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    
    # Transcribed sequence.
    transcription = ''
    
    # Iterate over the sequence, transcribing as we go.
    for nucleotide in sequence:
    	transcription += transscription_dict[nucleotide]
    
    # Return the transcription.
    return transcription

def to_rna(dna):

    transcription = ''

    for nucleotide in dna:
        if nucleotide == 'G':
            transcription += 'C'
        elif nucleotide == 'C':
            transcription += 'G'
        elif nucleotide == 'T':
            transcription += 'A'
        elif nucleotide == 'A':
            transcription += 'U'

    return transcription

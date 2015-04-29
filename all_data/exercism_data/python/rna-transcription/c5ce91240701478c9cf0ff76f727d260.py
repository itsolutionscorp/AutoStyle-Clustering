transcription = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
def to_rna(dna_strand):
    new_strand = ''
    for letter in dna_strand:
        nucleo = letter.upper()
        if nucleo in transcription:
            new_strand += transcription[nucleo]
        else:
            new_strand += letter
    return new_strand

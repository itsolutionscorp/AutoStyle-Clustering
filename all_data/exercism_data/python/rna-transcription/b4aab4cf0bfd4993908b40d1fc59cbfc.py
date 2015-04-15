# Rna Transcription


def to_rna(dna_str):
    ''' takes a string of letters representing DNA nucleotides i.e.'ACGT'
    returns RNA complement i.e. 'UGCA" '''
    conversion = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna_str = ""
    for letter in dna_str:
        try:
            rna_str += conversion[letter]
        except KeyError:
            return "Error: Not a valid DNA sequence."
    return rna_str

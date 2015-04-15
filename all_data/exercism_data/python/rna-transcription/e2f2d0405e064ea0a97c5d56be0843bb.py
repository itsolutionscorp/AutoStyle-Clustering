# Rna Transcription


def to_rna(dna_str):
    ''' takes a string of letters representing DNA nucleotides
    i.e.'ACGT' returns RNA complement i.e. 'UGCA" '''
    conversion = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    try:
        rna_str = [conversion[letter] for letter in dna_str]
        return rna_str
    except ValueError:
            print "Not a valid DNA sequence."

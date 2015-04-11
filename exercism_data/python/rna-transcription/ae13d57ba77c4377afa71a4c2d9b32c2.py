__author__ = 'Arno'

def to_rna(dna_string):
    dna_string = dna_string.upper()
    rna_string = ''
    for i in dna_string:
        if i == 'A':
            rna_string += 'U'
        elif i == 'G':
            rna_string += 'C'
        elif i == 'C':
            rna_string += 'G'
        elif i == 'T':
            rna_string += 'A'
        else:
            rna_string = 'No valid transcription'
            break
    return rna_string

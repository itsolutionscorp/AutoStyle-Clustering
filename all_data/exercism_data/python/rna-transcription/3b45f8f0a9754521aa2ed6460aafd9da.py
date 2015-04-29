'''Module for DNA->RNA transcription'''

def to_rna(dna):
    return_str = ''
    i = 0
    for base in dna:
        if base == 'C':
            return_str = return_str + 'G'
            i = i + 1
        elif base == 'G':
            return_str = return_str + 'C'
            i = i + 1
        elif base == 'A':
            return_str = return_str + 'U'
            i = i + 1
        elif base == 'T':
            return_str = return_str + 'A'
            i = i + 1
        else:
            return 'Invalid base at ' + (i + 1)
    return return_str

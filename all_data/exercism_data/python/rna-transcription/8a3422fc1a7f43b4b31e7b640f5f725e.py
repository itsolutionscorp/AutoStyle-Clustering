'''rna exercise'''

def to_rna(dna):
    '''return the RNA transcription of the DNA iterable'''
    trans = {
        'G':'C',    # guanine -> cytosine
        'C':'G',    # cytosine -> guanine
        'T':'A',    # thymidine -> adenine
        'A':'U',    # adenine -> uracil
    }

    return ''.join(trans[nucleotide] for nucleotide in dna)

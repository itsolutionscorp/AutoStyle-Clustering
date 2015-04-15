
# Added error checking


def to_rna(dna_seq):
    dna = 'GCTA'
    rna = 'CGAU'
    z = {}

    for ch1, ch2 in zip(dna, rna):
        z[ch1] = ch2

    rna_seq = ''
    for s in dna_seq:
        if s not in dna:
            break
        rna_seq += z[s]
    ''' If there was an error in the dna_seq the returned rna_seq will be
        shorter than expected and it's length will point to the error.
    '''    
    return rna_seq

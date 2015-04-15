def get_basepair(DNA):
    if DNA == 'C':
        return 'G'
    elif DNA == 'G':
        return 'C'
    elif DNA == 'A':
        return 'U'
    elif DNA == 'T':
        return 'A'
    else:
        print( "Error! Bad input to get_basepair!" )

def to_rna(dna):
    rna = ''
    for bp in dna:
        rna = rna + get_basepair(bp)
    return rna

rnaEquivalents = { 'C' : 'G', 'G' : 'C', 'T' : 'A', 'A' : 'U' }

def to_rna(input):
    retval = ''
    for i in input:
        retval += rnaEquivalents[i]
    return retval

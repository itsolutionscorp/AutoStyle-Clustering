rnaEquivalents = { 'C' : 'G', 'G' : 'C', 'T' : 'A', 'A' : 'U' }

def to_rna(input):
    retval = ''
    for i in input:
        if i not in rnaEquivalents:
            raise TypeError('Input character ' + i + ' is not a valid DNA base symbol')
        retval += rnaEquivalents[i]
    return retval

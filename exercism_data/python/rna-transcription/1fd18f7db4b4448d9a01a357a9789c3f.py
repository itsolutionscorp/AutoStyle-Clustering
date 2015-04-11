def pivot(c):
    ''' return the new value and ? if unkown nucleotide '''
    if c == 'G':        return 'C'
    elif c == 'C':      return 'G'
    elif c == 'T':      return 'A'
    elif c == 'A':      return 'U'
    return '?'

        
def to_rna(text):
    return ''.join([pivot(l) for l in text])

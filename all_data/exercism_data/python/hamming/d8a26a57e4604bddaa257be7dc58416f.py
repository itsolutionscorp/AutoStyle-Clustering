# hamming.py

def distance(dna01, dna02):
    """
        Assumes dna01 and dna02 are strings
        of equal length.
        Returns an int of the number of variances
        between the two strings (hamming length)
    """
    assert len(dna01) == len(dna02),\
           '%s and %s are of unequal length.' %(dna01, dna02)
    
    if len(dna01) < 1:
        if dna01 != dna02:
            return 1
        else:
            return 0
    else:
        if dna01[:1] != dna02[:1]:
            return 1 + distance(dna01[1:], dna02[1:])
        else:
            return 0 + distance(dna01[1:], dna02[1:])


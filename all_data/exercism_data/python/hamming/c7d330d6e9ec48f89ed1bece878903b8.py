'''
    GAGCCTACTAACGGGAT
    CATCGTAATGACGGCCT
    ^ ^ ^  ^ ^    ^^

The Hamming distance between these two DNA strands is 7.

# Implementation notes

The Hamming distance is only defined for sequences of equal length. This means
that based on the definition, each language could deal with getting sequences
of equal length differently.
'''

def distance(seq1,seq2):
    if seq1.__len__() <> seq2.__len__():
        raise RuntimeError("sequence lengths do not match")
        return False
    hamdistance = 0
    for x in range(seq1.__len__()):
        if seq1[x] <> seq2[x]:
            hamdistance += 1
    
    return hamdistance

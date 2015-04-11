""" Calculates the Hamming difference between two DNA strands

Specification:

The Hamming distance is only defined for sequences of equal length.
Count the number of differences between two homologous DNA strands.
The count gives a measure of the minimum number of point mutations
that could have occurred on the evolutionary path between the two strands.

    GAGCCTACTAACGGGAT
    CATCGTAATGACGGCCT
    ^ ^ ^  ^ ^    ^^

The Hamming distance between these two DNA strands is 7.
"""

def distance(dna1, dna2):
    """ count the differences between the two strings

    compare each character position n the two strings and return the count
    of differing ones
    """
    count = 0
    for i in range(0, len(dna1)):
        if dna1[i] != dna2[i]:
            count = count + 1
    return count

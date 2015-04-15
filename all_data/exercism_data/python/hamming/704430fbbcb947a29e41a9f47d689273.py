def distance(strand1, strand2):
    """
    The distance function takes in two DNA strands to compare and
    returns their Hamming Distance.

    Hamming Distance - If a nuleotide in the a strand is different
    than its conterpart in the other strand, we add one to the distance.
    Thus, if one strand is 'GATACA'' and the second is 'GCATAA', the
    hamming distance is 4 because 4 of the letters are different.
    
    """
    
    hammingDistance = 0
    
    for i in xrange(0, len(strand1)):
        if (strand1[i] != strand2[i]):
            hammingDistance += 1

    return hammingDistance

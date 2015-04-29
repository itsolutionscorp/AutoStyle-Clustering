#
# captainnurple's code for the Python Hamming Distance exercise.
#
def distance(strand1, strand2):    
    # First ensure input strands are equal length
    assert len(strand1) == len(strand2), "Strands must be equal length"

    # Now loop through and count differences
    hammingDist = 0    
    for i, c in enumerate(strand1):
        if ( c != strand2[i] ): hammingDist += 1 #increment hammingDist for each inequality

    return hammingDist

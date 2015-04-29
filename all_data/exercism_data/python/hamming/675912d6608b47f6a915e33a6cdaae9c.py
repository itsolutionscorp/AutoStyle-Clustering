#
# captainnurple's code for the Python Hamming Distance exercise.
#
# distance method takes two strands of equal length and compares them
# to find the "hamming distance" -- essentially the number of non-equal
# base pairs.
def distance(strand1, strand2):    
    assert len(strand1) == len(strand2), "Strands must be equal length"

    # Now loop through and count differences
    # This loop iterates through each character of strand1 and compares it to
    # the corresponding character in strand2
    hammingDist = 0    
    for i, c in enumerate(strand1):
        if ( c != strand2[i] ): hammingDist += 1 #increment hammingDist for each inequality

    return hammingDist

def hamming(strand1,strand2):

    # assign strings to length based variables
    # assume strand1 is shortes
    short_strand = strand1
    long_strand = strand2

    # ensure that any difference between the length of the two
    # dna strings are accounted for as a hamming distance
    iter = abs(len(strand1)-len(strand2))

    # Correct assumption in case it is false
    if len(strand1) > len(strand2):
        short_strand = strand2
        long_strand = strand1

    # iterate through all the characters in the short string
    for i in range(0,len(short_strand)):
        # add one to the iterator if characters do not match
        if short_strand[i] != long_strand[i]:
            iter += 1

    return iter

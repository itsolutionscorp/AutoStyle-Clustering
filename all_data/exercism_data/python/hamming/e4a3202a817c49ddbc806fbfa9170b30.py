def distance(strand_1, strand_2):
    hamming_distance = 0
    # compare strands to avoid exhaustive computations
    if strand_1 == strand_2:
        return 0
    # if we are not so lucky 
    # get nucleotid of 1st strand and its index
    for i, strand_1_nucl in enumerate(strand_1):
        # if nucleotid of iterated strand 
        # is not equal to nucleotid of second strand
        # under the same index
        if strand_1_nucl != strand_2[i]:
            # increase the distance
            hamming_distance += 1
    # cross your fingers
    return hamming_distance

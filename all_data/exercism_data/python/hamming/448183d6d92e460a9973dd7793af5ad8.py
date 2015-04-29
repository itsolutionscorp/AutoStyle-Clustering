def distance(dna1, dna2):
    """Returns the number of differences between two DNA strands."""

    if len(dna1) == len(dna2):
        count = 0
        for i in range(0, len(dna1)):
            if dna1[i] != dna2[i]:
                count += 1
        return count
    else:
        print "Sequences are not equal in length."
        return

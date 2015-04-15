def distance(strand1, strand2):

    hamming = 0
    
    if len(strand1) != len(strand2):
        raise Exception("Different length strands")

    for i in range(0, len(strand1)):
        if strand1[i] != strand2[i]:
            hamming += 1

    return hamming

    

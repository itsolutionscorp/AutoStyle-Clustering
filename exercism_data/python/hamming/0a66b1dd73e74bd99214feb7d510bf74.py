def distance(dna1, dna2):
    """Calculate the Hamming Distance between two DNA strands"""
    if(len(dna1) != len(dna2)):
        return -1
    hamming_distance = 0
    for index in range(len(dna1)):
        if(dna1[index] != dna2[index]):
            hamming_distance += 1
    return hamming_distance

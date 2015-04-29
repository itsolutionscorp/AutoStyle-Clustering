def distance(strand1, strand2):
    strand_distance = 0;
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            strand_distance = strand_distance + 1;
    
    return strand_distance;

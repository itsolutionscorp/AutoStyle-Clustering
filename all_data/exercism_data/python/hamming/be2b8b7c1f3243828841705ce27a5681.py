def distance(strand_1, strand_2):
    if len(strand_1) != len(strand_2):
        return
    dist = 0
    for pos in range(len(strand_1)):
        if strand_1[pos] != strand_2[pos]:
            dist += 1
    return dist
    

def distance(strand1, strand2):
    difference = 0
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            difference += 1    
    return difference

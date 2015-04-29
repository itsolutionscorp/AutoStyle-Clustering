def distance(strand1, strand2):
    hamming_distance = 0
    for index in range(len(strand1)):
        if strand1[index] != strand2[index]:
            hamming_distance+=1
    return hamming_distance

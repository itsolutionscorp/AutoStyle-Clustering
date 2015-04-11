def hamming_distance(strand_1, strand_2):
    result = 0
    for index in range(min(len(strand_1), len(strand_2))):
        if strand_1[index] != strand_2[index]:
            result += 1
    return result

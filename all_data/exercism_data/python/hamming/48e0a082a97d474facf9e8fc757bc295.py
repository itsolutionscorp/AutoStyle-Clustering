def distance(strand1, strand2):
    hamming_count = 0
    for pair in zip(strand1, strand2):
        if pair[0] != pair[1]:
            hamming_count +=1
    return hamming_count

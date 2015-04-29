def distance(strand1, strand2):
    hamming_count = 0
    pairs = zip(strand1, strand2)
    for i in range(len(pairs)):
        if pairs[i][0] != pairs[i][1]:
            hamming_count +=1
    return hamming_count

def hamming(strand1, strand2):
    count = abs(len(strand1) - len(strand2))  # init count with len difference
    for a, b in zip(strand1, strand2):
        if a != b:  # count the rest of the differences
            count += 1
    return count

def hamming(strand1, strand2):
    count = abs(len(strand1) - len(strand2))
    for base1, base2 in zip(strand1, strand2):
        count += base1 != base2

    return count

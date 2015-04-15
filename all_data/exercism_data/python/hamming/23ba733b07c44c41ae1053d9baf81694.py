def hamming(strand1, strand2):
    count = 0
    for char1, char2 in map(None, strand1, strand2):
        if char1 != char2:
            count += 1
    return count

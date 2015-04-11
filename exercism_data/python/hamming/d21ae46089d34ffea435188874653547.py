def distance(strand1, strand2):
    differences = 0
    strand_length = len(strand1)
    index = 0
    while index < strand_length:
        if strand1[index] != strand2[index]:
            differences += 1
        index += 1
    return differences

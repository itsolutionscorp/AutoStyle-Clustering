def distance(strand1, strand2):
    count = 0
    if len(strand1) == len(strand2):
        for i in range(len(strand1)):
            count += strand1[i] != strand2[i]
    return count

def distance(strand1, strand2):
    return sum(1 for i in range(len(strand1)) if strand1[i] != strand2[i])

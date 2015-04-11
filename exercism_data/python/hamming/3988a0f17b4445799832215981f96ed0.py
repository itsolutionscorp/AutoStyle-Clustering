def distance(strand_a, strand_b):
    count = 0
    for i in range(len(strand_a)):
        if strand_a[i] != strand_b[i]:
            count = count + 1
    return count

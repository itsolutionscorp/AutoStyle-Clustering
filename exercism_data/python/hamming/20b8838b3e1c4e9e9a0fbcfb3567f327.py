def distance(strand_a, strand_b):
    count = 0
    for idx, letter in enumerate(strand_a):
        if letter != strand_b[idx]:
            count += 1
    return count

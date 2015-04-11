def distance(strand_a, strand_b):
    count = 0
    for a, b in zip(strand_a, strand_b):
        if a != b:
            count += 1
    return count

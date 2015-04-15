def distance(dna1, dna2):
    diff_count = 0
    for d1, d2 in zip(dna1, dna2):
        if d1 != d2:
            diff_count += 1
    return diff_count

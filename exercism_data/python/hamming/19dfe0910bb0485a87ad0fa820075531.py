def distance(seq_a, seq_b):
    length = min(len(seq_a), len(seq_b))

    diff = 0
    for i in range(length):
        if seq_a[i] != seq_b[i]:
            diff += 1

    return diff

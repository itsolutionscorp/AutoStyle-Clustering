def hamming(seq_a, seq_b):
    count = 0
    compare_seq = zip(seq_a, seq_b)
    for i in compare_seq:
        if i[0] != i[1]:
            count += 1
    if len(seq_a) != len(seq_b):
        count += abs(len(seq_a) - len(seq_b))
    return count

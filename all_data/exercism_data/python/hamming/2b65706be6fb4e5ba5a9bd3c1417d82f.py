def hamming(seq_a, seq_b):
    count = 0
    # Create a sequence of grouped elements such that seq_a[0] and seq_b[0]
    # becomes [(seq_a[0], seq_b{0])] to be iterated over and compared.
    compare_seq = zip(seq_a, seq_b)
    for i in compare_seq:
        if i[0] != i[1]:
            count += 1
    # When sequence lengths are not equal, add the difference to the count.
    if len(seq_a) != len(seq_b):
        count += abs(len(seq_a) - len(seq_b))
    return count

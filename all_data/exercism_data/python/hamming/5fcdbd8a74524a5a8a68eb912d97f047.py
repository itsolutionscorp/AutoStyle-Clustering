def hamming(strand_1, strand_2):
    larger = len(strand_2)
    smaller = len(strand_1)
    if len(strand_1) > len(strand_2):
        larger = len(strand_1)
        smaller = len(strand_2)

    ham = larger - smaller # 0 when equal length, also tasty when cooked right

    for i in range(0, smaller):
        if strand_1[i] != strand_2[i]:
            ham += 1

    return ham







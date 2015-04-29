def hamming(strand_1, strand_2):
    ham = abs(len(strand_1) - len(strand_2)) # 0 when equal length, also tasty when cooked right

    for i in range(0, min(len(strand_1), len(strand_2))):
        if strand_1[i] != strand_2[i]:
            ham += 1

    return ham







def hamming(strand, copy):
    hamming_num = 0
    N = max(len(strand),len(copy))
    for i in range(N):
        try:
            if strand[i] != copy[i]:
                hamming_num += 1
        except IndexError:
            hamming_num += 1
    return hamming_num

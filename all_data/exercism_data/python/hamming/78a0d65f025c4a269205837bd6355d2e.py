def hamming(s1, s2):
    ham_dist = 0

    for i in range(0, len(s1)):
        if i >= len(s2):
            ham_dist += 1
        elif s1[i] != s2[i]:
            ham_dist += 1

    if len(s1) < len(s2):
        ham_dist += (len(s2) - len(s1))

    return ham_dist

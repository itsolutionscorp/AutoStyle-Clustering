def distance(S1, S2):
    if len(S1) != len(S2):
        return -1
    else:
        return sum([S1[i] != S2[i] for i in range(len(S1))])

def hamming(in1, in2):

    if len(in1) <= len(in2):
        s1 = in1
        s2 = in2
    else:
        s1 = in2
        s2 = in1

    dif = len(s2) - len(s1)

    for i in range(0, len(s1)):
        if s1[i] != s2[i]:
            dif += 1

    return dif

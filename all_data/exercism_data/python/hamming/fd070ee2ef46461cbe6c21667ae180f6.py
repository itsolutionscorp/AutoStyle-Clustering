def distance(in1, in2):
    d = 0
    for i in range(len(in1)):
        if in1[i] != in2[i]:
            d = d + 1
    return d

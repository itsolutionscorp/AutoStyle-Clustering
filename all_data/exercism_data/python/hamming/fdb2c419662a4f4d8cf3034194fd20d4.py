def hamming(strand_a, strand_b):
    a_length = len(strand_a)
    b_length = len(strand_b)
    if a_length != b_length:
        if a_length > b_length:
            strand_b = '{:*<{width}}'.format(strand_b,width=a_length)
        else:
            strand_a = '{:*<{width}}'.format(strand_a,width=b_length)
    distance = 0
    for i in range(len(strand_a)):
        if strand_a[i] != strand_b[i]:
            distance += 1
    return distance
    

def hamming(s1, s2):
    hamming_distance = 0
    for i, j in zip(s1+' '*(len(s2)-len(s1)), s2+' '*(len(s1)-len(s2))):
        if i != j:
            hamming_distance += 1
    return hamming_distance

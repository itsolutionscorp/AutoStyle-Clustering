def distance(sequence1, sequence2):
    dis = 0
    for i in range(len(sequence1)):
        if sequence1[i] != sequence2[i]:
            dis += 1
    return dis

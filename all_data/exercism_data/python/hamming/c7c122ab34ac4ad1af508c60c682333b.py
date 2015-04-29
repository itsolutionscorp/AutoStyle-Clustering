def distance(first, second):
    hdist = 0
    for i in range(len(first)):
        if not first[i] == second[i]:
            hdist += 1
    return hdist

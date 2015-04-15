def sieve(n):
    nlist = []
    for i in range(0, n+1):
        nlist.append([i, True])
    start = 2
    while start < n:
        for j in range(start, n+1, start):
            if j != start:
                nlist[j][1] = False
        for j in range(start+1, n+1):
            if nlist[j][1] == True:
                start = j
                break
        else:
            break
    result = []
    for i in range(2, n+1):
        if nlist[i][1]:
            result.append(nlist[i][0])
    return result

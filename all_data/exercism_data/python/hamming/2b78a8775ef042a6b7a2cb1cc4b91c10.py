def distance(pa, ma):
    ham = 0
    for i in range(len(pa)):
        if pa[i-1] != ma[i-1]:
            ham += 1
    return ham

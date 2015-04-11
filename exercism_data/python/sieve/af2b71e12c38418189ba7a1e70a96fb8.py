def sieve(limit):
    start_set = []
    multi_set = []
    for i in range(2,limit):
        start_set.append(i)
        for j in range(2,limit):
            if i != j and j%i == 0:
                if j not in multi_set:
                    multi_set.append(j)
    for n in multi_set:
        if n in start_set:
            start_set.remove(n)
    return start_set

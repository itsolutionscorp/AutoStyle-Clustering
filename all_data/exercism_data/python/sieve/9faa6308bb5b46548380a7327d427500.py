def sieve(num):
    l = range(2,num)
    remove = []
    for i in l:
        [remove.append(i*x) for x in l if i*x in l]
    [l.remove(x) for x in remove if x in l]
    return l

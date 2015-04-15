def sieve(num):
    l = range(2,num)
    l2 = range(2,num)
    for i in l:
        [l2.remove(i*x) for x in l if i*x < num and i*x in l2]
    return l2

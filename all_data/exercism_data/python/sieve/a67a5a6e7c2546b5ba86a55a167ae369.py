def sieve(num):
    numlist = [x for x in range(2, num + 1)]
    for x in numlist:
        for y in numlist:
            if y!= x and y % x == 0:
                numlist.remove(y)
    return numlist

def sieve(number):
    end = number + 1
    array = range(end)

    array[1] = 0
    for i in xrange(2, number/2):
        if array[i] > 0:
            for j in xrange(i+i, end, i):
                array[j] = 0

    return [x for x in range(2, end) if array[x] > 0]

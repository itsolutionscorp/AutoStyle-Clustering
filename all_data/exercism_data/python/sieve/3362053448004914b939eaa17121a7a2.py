def sieve(number):
    end = number + 1
    array = [0] * end

    for i in xrange(2, number/2):
        if array[i] == 0:
            for j in xrange(i+i, end, i):
                array[j] = 1

    return [x for x in range(2, end) if array[x] == 0]

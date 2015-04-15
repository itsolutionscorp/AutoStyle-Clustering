def nth_prime(remain):
    prime = [2]

    if remain == 1:
        return 2

    remain -= 1
    limit = 0
    test = 1

    while True:
        test += 2
        if prime[limit] ** 2 < test:
            limit += 1

        for index in xrange(1, limit+1):
            if test % prime[index] == 0:
                break
        else:
            prime.append(test)
            remain -= 1
            if remain == 0:
                break

    return test

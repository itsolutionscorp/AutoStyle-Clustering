def prime_factors(arg):
    ret = []
    while True:
        if arg < 2:
            break

        temp = arg
        for i in range(2, temp + 1):
            if arg % i == 0:
                arg /= i
                ret.append(i)
                break
    return ret

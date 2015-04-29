def sieve(end):
    end += 1
    flags = [True] * end
    flags[0] = flags[1] = False

    for number, flag in enumerate(flags):
        if flag:
            for i in range(number * number, end, number):
                flags[i] = False

    return [i for i, f in enumerate(flags) if f]

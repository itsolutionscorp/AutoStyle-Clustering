def sieve(num):
    num_list = [True] * (num + 1)
    for i in range(2, num):
        if not num_list[i]:
            continue
        for x in range(2, num):
            if i * x > num:
                break
            num_list[i*x] = False # Not prime
    return [i for i in range(2, num + 1) if num_list[i]]

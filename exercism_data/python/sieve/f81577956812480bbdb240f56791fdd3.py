def sieve(number):
    num_list = []
    for i in range(number+1):
        if i == 0 or i == 1:
            continue
        num_list.append(i)
    for prime in num_list:
        for composite in num_list:
            if composite % prime == 0 and prime != composite:
                num_list.remove(composite)
    return num_list

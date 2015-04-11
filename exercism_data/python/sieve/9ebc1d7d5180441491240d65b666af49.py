def sieve(limit):
    working_list = range(2, limit+1)
    for i in range(2, (limit+1)/2):
        for item in working_list:
            if item % i == 0 and item != i:
                working_list.remove(item)
    return working_list

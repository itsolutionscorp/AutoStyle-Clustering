def sieve(limit):
    '''returns a list of prime numbers less than limit using
    sieve of Erasthenes method'''
    list_of_nums = [i for i in range(2, limit)]
    for num in list_of_nums:
        multiplier = 2
        while num * multiplier <= limit:
            try:
                list_of_nums.remove(num * multiplier)
            except ValueError:
                next
            multiplier += 1
    return list_of_nums

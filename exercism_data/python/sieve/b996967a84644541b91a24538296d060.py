def sieve(limit):
    nums = range(2, limit)
    for n in nums:
        for i in range(2, limit/n+1):
            if n*i < limit:
                if n*i in nums:
                    nums.remove(n*i)

    return nums

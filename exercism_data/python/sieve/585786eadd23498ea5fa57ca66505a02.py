def sieve(n):
    nums = range(2,n+1)
    for i in nums:
        m = i
        while(i*m <= n):
            try:
                nums.remove(i*m)
            except ValueError:
                pass
            m += 1
    return nums

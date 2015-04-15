def sieve(n):
    nums = list(range(2, n+1))
    prime = min(nums)
    nxt = 2 * prime
    while prime != max(nums):
        while nxt <= n:
            if nxt in nums:
                nums.remove(nxt)
            nxt += prime
        prime = nums[nums.index(prime)+1]
        nxt = 2 * prime
    return nums

def sieve(n):
    nums = [i for i in xrange(2, n + 1)]
    i = 0
    while i < len(nums):
        prime = nums[i]
        for j in xrange(2, n/prime + 1):
            m = j * prime
            if m in nums:
                nums.remove(j * prime)
        i += 1
    return nums

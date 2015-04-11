def sieve(n):
    nums = range(2, n + 1)
    for i, num in enumerate(nums):
        nums = nums[:i+1] + filter(lambda x: (x % num) != 0, nums[i+1:])
    return nums

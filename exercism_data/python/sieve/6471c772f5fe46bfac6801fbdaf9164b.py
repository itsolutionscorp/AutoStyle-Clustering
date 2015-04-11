from math import sqrt

def sieve(n):
    nums = [True for _ in range(n+1)]
    nums[0], nums[1] = False, False
    for i in range(int(sqrt(len(nums))) + 1):
        if nums[i]:
            for j in range(i*i, len(nums), i):
                nums[j] = False
    return [i for i, prime in enumerate(nums) if prime]

def remove_multiples(n, m):
        return [i for i in n if i % m != 0 or i == m]

def sieve(num):
    nums = range(2, num)
    i = 0
    while nums[i] < nums[-1]:
        nums = remove_multiples(nums, nums[i])
        i += 1
    return nums

print sieve(100)

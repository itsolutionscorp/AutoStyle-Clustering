def slices(string, n):
    if n > len(string):
        raise ValueError('n is at most the length of string.')
    if n < 1:
        raise ValueError('n must be positive.')
    nums = [int(x) for x in string]
    return list(nums[i:i+n] for i in range(len(nums)-n+1))

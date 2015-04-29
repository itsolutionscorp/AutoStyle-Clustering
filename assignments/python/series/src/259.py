def slices(string, size):
    if len(string) < size or size == 0:
        raise ValueError
    nums = list(map(int, string))
    rv = []
    while size <= len(nums):
        rv.append(nums[:size])
        del nums[0]
    return rv

'''exer series'''

def slices(nums, size):
    '''nums is a string of digits, return list of all slices of len size'''
    num_nums = len(nums)
    if size <= 0 or size > num_nums:
        raise ValueError

    nums = [int(x) for x in nums]   # cast it into a list of ints
    results = []
    # don't need to eval those that go past the end of the list
    for start in range(num_nums - size + 1):
        results.append(nums[start:start+size])
    return results

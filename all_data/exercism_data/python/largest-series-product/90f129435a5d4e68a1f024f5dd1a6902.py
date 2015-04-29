from operator import mul


def slices(nums, slicings):
    if slicings > len(nums):
        raise ValueError
    nums = map(int, nums)
    return [nums[x:x+slicings] for x in range(len(nums)) if x+slicings <= len(nums)]


def largest_product(nums, slicings):
    if not nums:
        return 1
    return reduce(mul, max(slices(nums, slicings), key=lambda x: reduce(mul, x)))

from functools import reduce
from operator import mul


def largest_product(nums: str, n: int) -> int:
    "Return the largest product of a sub-sequence length `n`."
    numbers = list(map(int, nums))
    return max(reduce(mul, p, 1) for p in slices(numbers, n))


def slices(nums: str, n: int) -> list:
    "Return a list of slices of integers from `nums`."
    if len(nums) < n:
        raise ValueError("n (%d) must be less than the length of "
                         "chars (%d)" % (n, len(nums)))
    return [list(map(int, nums[i:i+n])) for i in range(len(nums))
            if len(nums[i:i+n]) >= n] or [[]]

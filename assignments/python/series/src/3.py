from series import slices
from functools import reduce
import operator
def slices(nums, length):
    if not nums: return 1
    tmp = 0
    list_of_slices = slices.slices(nums, length)
    for sub_list in list_of_slices:
        if reduce(operator.mul, sub_list) > tmp:
            tmp = reduce(operator.mul, sub_list)
    return tmp

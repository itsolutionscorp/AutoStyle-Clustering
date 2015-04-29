from functools import reduce  
from operator import mul

def slices(digits, size):
    digits_size = len(digits)
    if 0 <= size <= digits_size:
        slice_count = digits_size - size 
        return [[int(d) for d in digits[s : s+size]] 
                        for s in range(slice_count + 1)]
    else:
        raise ValueError('size is out of range: 0 <= %d <= %d' % (size, digits_size))
  
def product(nums): 
    return reduce(mul, nums, 1)
    
def largest_product(number, size):
    if number and not number.isdigit():
        raise ValueError('%s is not a valid digit list.' % number)    
    return max(product(digits) for digits in slices(number, size))

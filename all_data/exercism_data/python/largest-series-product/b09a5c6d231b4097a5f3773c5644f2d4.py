from functools import reduce  

def slices(digits, size):
    digits_size = len(digits)
    if 0 <= size <= digits_size:
        last_slice_index = digits_size - size 
        return [[int(j) for j in digits[i:i+size]] for i in range(last_slice_index + 1)]
    else:
        raise ValueError('size is out of range 0 <= %d <= %d' % (size, digits_size))
  
def product(nums): 
    return reduce(lambda x, y: x * y, nums, 1)
    
def largest_product(digits, size):
    if digits and not digits.isdigit():
        raise ValueError('%s is not a valid digit list.' % digits)    
    return max(product(ds) for ds in slices(digits, size))

import operator

def largest_product(nums, size):
    if not nums or not size:
        return 1
    
    return max( reduce(operator.mul, group, 1) for group in slices(nums, size) ) 

def slices(nums, size):
    if size < 1:
        raise ValueError('Requested size is too small!')
    
    if size > len(nums):
        raise ValueError('Requested size is too big!')
    
    return [ map(int, nums[i:i+size]) for i in xrange( len(nums) - size + 1) ]

import operator
import itertools

def largest_product(num_string, r):
    
    if r > len(num_string):
        raise ValueError
    if r == 0:
        return 1
    #I don't unterstand why r == 0 should return 1 but the test calls for it
    
    return max(reduce(operator.mul, s, 1) for s in slices(num_string, r))
    #the reduce expression used like this takes the product of a list

    
def slices(num_string, r):
    #from 'series' assignment
    
    if r > len(num_string):
        raise ValueError
        
    return [map(int, num_string[i:i+r]) for i in xrange(len(num_string)-r+1)]

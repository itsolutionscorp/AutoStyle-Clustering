def largest_product(digits, length):

    digits = slices(digits,length)
    
    max = 0
    
    for digslice in digits:
        prod = 1
        for i in digslice:
            prod *= i
        if prod > max:
            max = prod
            
    return max
    
def slices(digits, size):
    digitlength = len(digits)
    
    if size > digitlength:
        raise ValueError('Size cannot be greater than number of digits')
        
    return [strToIntList(digits)[i:i+size] for i in xrange(0,digitlength-size+1)]
    
def strToIntList(digits):
    return map(int,list(digits))

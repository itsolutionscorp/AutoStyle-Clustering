from operator import mul

def largest_product(seq, length):
    
    if length == 0:
        return 1
    else:
        return max(find_product(x) for x in slices(seq, length))
        
        
def find_product(elements):
    return reduce (mul, elements)        
   
def slices(seq, length):
    
    if len(seq) < length or length == 0:
        raise ValueError( 
            "{} not a valid slice length for series length {}".format(
                length, len(seq)))       
                 
    digits = [int(d) for d in seq]
    return [digits[i: i+length]
            for i in xrange(len(seq)-length + 1)]

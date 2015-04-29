from operator import mul

def largest_product(numString, size):
    return max([reduce(mul,nums,1) for nums in slices(numString,size)])


def slices(numString, size):
    if size > len(numString): raise ValueError('Slice size is greater than the input length!')
    return [map(int,numString[i:i+size]) for i in range(0,len(numString)-size+1)]

from functools import reduce

def slices(series, n):
    if n<1 or n>len(series):
        raise ValueError()
    result = []
    for position in range(0,len(series)):
        if position+n<=len(series):
            slice = [int(i) for i in series[position:position+n]]
            result.append(slice)
    return result

def largest_product(series, n):
    if not series:
        return 1
    max=0
    for seq in slices(series,n):
        product = reduce(lambda x,y: x*y, seq)
        if product>max:
            max = product
    return max

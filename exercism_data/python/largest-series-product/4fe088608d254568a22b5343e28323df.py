import operator
def largest_product(str,n):
    nums = slices(str,n)
    return max([reduce(operator.mul,x,1) for x in nums])

def slices(str,n):
    tmp = []
    out = []
    if n > len(str):
        raise ValueError('Slice length is too long')
    for count in range(len(str)-n+1):
        for i in range(n):
            tmp.append(int(str[count+i:(count+i+1)]))
        out.append(tmp)
        tmp = []
    
    return out

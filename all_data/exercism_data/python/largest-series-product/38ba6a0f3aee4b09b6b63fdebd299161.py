from operator import mul

def slices(in_string, number):
    if number > len(in_string):
        raise ValueError("Length of series must not be larger than string")
    if number == 0:
        raise ValueError("Length of series must not be zero")
    outlist=[]
    for i in range(0,len(in_string)-number+1):
        temp = list(in_string[i:number+i])
        outlist.append([int(char) for char in temp])
    return outlist
        
def largest_product(in_string, number):
    if in_string == "" and number ==0:
        return 1
    slicelist = slices(in_string, number)
    return max([reduce(mul, list, 1) for list in slicelist])

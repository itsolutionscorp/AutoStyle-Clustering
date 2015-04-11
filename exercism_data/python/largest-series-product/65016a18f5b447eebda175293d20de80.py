from itertools import product

def slices(numberss, length): # numbersess, oh yes, preciouss... golum!
    if length > len(numberss):
        raise ValueError
    sliceses = [] # filthy hobbitses!
    for i in range(len(numberss)-length+1):
        sliceses.append([int(l) for l in numberss[i:i+length]])
    return sliceses

def largest_product(numberss, length):
    sliceses = slices(numberss, length)
    largest = 1

    for slice in sliceses:
        prod = 1
        for num in slice:
            prod*=num
        largest = max(largest, prod)

    return largest

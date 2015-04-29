def slices(string, length):
    if len(string) < length:
        raise ValueError()
    # The string is converted into a list of numbers...
    nums = list(int(n) for n in string)
    # Then, the resulting slices are generated.
    return list(nums[i:i+length] for i in range(0, len(nums)-length+1))

def largest_product(string, length):
    # Using the previously defined function, the slices
    # of numbers are generated.
    slcs = slices(string, length)
    # maxProd holds the largest product.
    maxProd = 0
    for slc in slcs:
        prod = 1
        for n in slc:
            prod *= n
        if prod > maxProd:
            maxProd = prod
    return maxProd

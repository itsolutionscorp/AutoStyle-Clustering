from functools import reduce
def slices(str,n):
    j = 0
    list = []

    if n > len(str):
        raise ValueError("Invalid combination")
    elif n == 0:
        return [[1]] 

    list2 = [int(i) for i in str]

    while j + n <= len(list2):
        list.append(list2[j:j + n])
        j += 1

    return list

def largest_product(str,n):
    product = 0

    for nums in slices(str,n):
        p = reduce(lambda x,y: x*y, nums)

        if p > product:
            product = p

    return product

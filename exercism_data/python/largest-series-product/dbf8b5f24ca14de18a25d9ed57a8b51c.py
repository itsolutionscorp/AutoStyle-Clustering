import math

def largest_product(series, n):

    products = [1]

    for x in slices(series, n):
        product = 1
        for number in x:
            product *= number
        products.append(product)
    return sorted(products)[-1]


def slices(digits, n):
    # return digits of length n
    if len(digits) < n:
        raise ValueError
    return list(list(int(y) for y in digits[x:x+n]) for x in range(0,len(digits)) if len(digits)-x+1 > n)

from operator import mul


def slices(digits, n):
    if n > len(digits) or n < 1:
        raise ValueError

    result = []

    for index in range(len(digits) - n + 1):
        result.append([int(num) for num in digits[index: index + n]])

    return result

def largest_product(digits, n):
    if n == 0:
        return 1
        
    s = slices(digits, n)
    return max([reduce(mul, l, 1) for l in s])

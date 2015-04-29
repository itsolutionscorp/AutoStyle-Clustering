def str2ints(s):
    return [int(i) for i in s]


def slices(numbers, n):

    if n < 1 or n > len(numbers):
        raise ValueError("invalid length '%d'" % n)

    return [ str2ints(numbers[i:i+n])
            for i in range(len(numbers) - n + 1)]


def product(numbers):
    p = 1

    for n in numbers:
        p *= n

    return p

def largest_product(numbers, n):
    if numbers == '' and n == 0:
        return 1

    return max(product(t) for t in slices(numbers, n))

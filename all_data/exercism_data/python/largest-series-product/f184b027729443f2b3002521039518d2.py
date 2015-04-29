def slices(digits, n):
    """ Gives all possible consecutive number
        series of length 'n' in 'digits'
    """

    if n > len(digits) or n < 1:
        raise ValueError("Your inputs don't make sense.")

    step1 = [digits[i:i + n] for i in range(len(digits) - n + 1)]
    step2 = [list(d) for d in step1]

    return [[int(digit) for digit in series] for series in step2]

def product(list):
    r = 1
    for i in list:
        r *= i
    return r

def largest_product(digits, n):
    """ Given a string of digits, calculates the largest product
        for a series of consecutive digits of length n.
    """
    if digits == "" and n == 0:
        return 1
    sliced = slices(digits, n)
    sums = [product(i) for i in sliced]
    return max(sums)

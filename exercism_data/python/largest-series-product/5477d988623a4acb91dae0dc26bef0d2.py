def prod(numbers):
    result = 1
    for n in numbers:
        result *= n
    return result

def largest_product(digits, number):
    return max(prod(s) for s in slices(digits, number))
    
def slices(digits, number):
    if len(digits) < number:
        raise ValueError
    return [[int(x) for x in digits[n:n+number]] for n in xrange(len(digits) - number + 1)]

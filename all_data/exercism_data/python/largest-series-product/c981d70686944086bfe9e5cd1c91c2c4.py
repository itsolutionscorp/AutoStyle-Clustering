from operator import mul

def largest_product(series, length):
    def product(digits):
        return reduce(mul, digits, 1)

    return max(
        product(slice) for slice
        in (slices(series, length))
    )

def slices(series, length):
    if not 0 <= length <= len(series): raise ValueError

    digits = [ int(digit) for digit in series ]
    return [
        digits[i:i+length]
        for i in range(len(digits) - length + 1)
    ]

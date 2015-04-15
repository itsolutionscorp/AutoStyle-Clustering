def slices(digits, length):

    if length > len(digits) or length < 0:
        raise ValueError("Series longer than string")

    return [map(int, list(digits[i:i + length]))
            for i in range(len(digits) - length + 1)]


def largest_product(string, p):

    if p > len(string) or p < 0:
        raise ValueError("Number is longer than product")

    return max([reduce(lambda x, y: x * y, s, 1) for s in slices(string, p)])

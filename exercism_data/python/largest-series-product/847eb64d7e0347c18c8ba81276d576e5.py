def slices(digits, length):

    if length > len(digits) or length < 0:
        raise ValueError("Series is longer than string")

    substrings = []
    for i in range(len(digits) - length + 1):
        substrings.append(map(int, list(digits[i: i + length])))

    return substrings


def largest_product(string, p):

    if p > len(string) or p < 0:
        raise ValueError("Number is longer than product")

    return max([reduce(lambda x, y: x * y, s, 1) for s in slices(string, p)])

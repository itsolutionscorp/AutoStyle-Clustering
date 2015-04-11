from operator import mul

def slices(series, length):
    if length == 0 or length > len(series):
        raise ValueError
    return [map(int, list(series[i:i + length])) for i in range(len(series) - length + 1)]


def largest_product(series, length):
    if length == 0 and len(series) == 0:
        return 1
    return max(reduce(mul, digits) for digits in slices(series, length))

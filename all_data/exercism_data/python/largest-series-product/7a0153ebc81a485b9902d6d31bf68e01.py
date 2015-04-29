def slices(string, length):
    if length < 0:
        raise ValueError('Required length of slice too short')
    if length > len(string):
        raise ValueError('Required length of slice too long')
    return [map(int, list(string[i:i + length])) for i in range(len(string) - length + 1)]


def largest_product(string, length):
    return max(reduce(lambda x, y: x * y, digits, 1) for digits in slices(string, length))

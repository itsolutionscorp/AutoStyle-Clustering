def slices(digits, n):
    if n is 0 or n > len(digits):
        raise ValueError()

    result = []
    for i in range(0, len(digits) - (n - 1)):
        result.append([int(x) for x in list(digits[i:n + i])])

    return result

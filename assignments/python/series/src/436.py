def slices(digits, n):
    if n > len(digits) or n < 1:
        raise ValueError
    result = []
    for index in range(len(digits) - n + 1):
        result.append([int(num) for num in digits[index: index + n]])
    return result

def largest_product(digits, n):
    """
    Return the largest product for an n-length slice of digits
    """
    groups = slices(digits, n)

    return max(reduce(lambda a, b: a * b, g, 1) for g in groups)


def slices(digits, n):
    """
    Return list of all possible n-length slices of digits
    """
    if n > len(digits):
        raise ValueError("Sequence is too short")

    return [map(int, digits[i:i+n])
            for i in range(0, len(digits)-n+1)]

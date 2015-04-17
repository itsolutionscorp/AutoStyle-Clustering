def slices(digits, n):
    if n > len(digits):
        raise ValueError("Error: you ask for a {0}-digit series from a {1}-digit string".format(n, len(digits)))
    if n == 0:
        raise ValueError("Error: you ask for a 0-digit series")
    series = []
    digits = map(int, list(digits))
    for i in range(len(digits) - n + 1):
        item = digits[i:i+n]
        if not item in series:
            series.append(item)
    return series

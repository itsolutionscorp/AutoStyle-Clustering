def slices(digits, n):
    N = len(digits)
    if n == 0 or n > N: raise ValueError
    return [[int(digit) for digit in digits[i:i+n]] for i in range(N - n + 1)]

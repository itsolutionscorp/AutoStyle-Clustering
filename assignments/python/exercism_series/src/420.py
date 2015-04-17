def slices(digits, length):
    if not len(digits) >= length > 0:
        raise ValueError(
            "{} is not a valid length for a series with {} digits".format(
                length, len(digits)))
    digits = [int(d) for d in digits]
    series = [digits[i:i + length]
         for i in range(len(digits) - length + 1)]
    return series

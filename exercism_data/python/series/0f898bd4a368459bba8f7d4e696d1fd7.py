def slices(digits, length):
    if not 1 <= length <= len(digits):
        raise ValueError(
            "{} is not a valid slice length for a series of length {}".format(
                length, len(digits)))

    digits = [int(d) for d in digits]
    return [digits[i:i + length]
            for i in range(len(digits) - length + 1)]

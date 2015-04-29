def parse_binary(digits):
    result = 0
    for d in digits:
        if d == '0':
            result = 2 * result
        elif d == '1':
            result = 2 * result + 1
        else:
            raise ValueError(repr(d) + "is not a valid binary digit")
    return result

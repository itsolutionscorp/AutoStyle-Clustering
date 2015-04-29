def slices(digits,n):
    digits_length = len(digits)
    if not isinstance(digits,basestring) or not isinstance(n,(int,long)) or \
            n not in range(1,digits_length+1) or not digits.isdigit():
        raise ValueError
    start = 0
    result = []
    while start + n <= digits_length:
        result.append([int(d) for d in digits[start:start+n]])
        start += 1
    return result

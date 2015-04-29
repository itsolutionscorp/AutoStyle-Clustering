def parse_octal(digits):
    result = 0
    for i, digit in enumerate(reversed(digits)):
        if not '0' <= digit < '8':
            raise ValueError("Invalid octal digit: " + digit)
        result += int(digit) * 8 ** i
    return result

def int_parser_factory(base):
    error_msg = '{!r} is not a valid digit in base ' + str(base)

    def f(digits):
        res = 0
        for exponent, digit in enumerate(reversed(digits.lower())):
            n = ord(digit) - (ord('a') - 10 if (digit >= 'a') else ord('0'))
            if 0 <= n < base:
                res += n * base ** exponent
            else:
                raise ValueError(error_msg.format(digit))
        return res

    return f

hexa = int_parser_factory(16)

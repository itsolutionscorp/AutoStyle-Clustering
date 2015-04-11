class Octal(object):
    def __init__(self, digits):
        self.decimal = 0
        for i, digit in enumerate(digits):
            if digit not in '01234567':
                raise ValueError('Invalid octal digit: %s' % digit)
            self.decimal += (1 << 3 * (len(digits) - i - 1)) * int(digit)

    def to_decimal(self):
        return self.decimal

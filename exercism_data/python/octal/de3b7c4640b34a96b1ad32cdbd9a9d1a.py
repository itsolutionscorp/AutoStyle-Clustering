class Octal(object):
    def __init__(self, data):
        self.digits = []
        for ch in data:
            if ch < '0' or ch > '7':
                raise ValueError, "Invalid octal digit: %s" % ch
            self.digits.append(int(ch))

    def to_decimal(self):
        base = 1
        value = 0
        for digit in reversed(self.digits):
            value += digit * base
            base *= 8
        return value

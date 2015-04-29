class Octal(object):
    def __init__(self, string):
        self.value = 0

        for digit in string:
            if not digit in '01234567':
                raise ValueError('Invalid octal digit: {}'.format(digit))

            self.value = self.value * 8 + int(digit);

    def to_decimal(self):
        return self.value

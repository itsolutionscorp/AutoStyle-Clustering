class Octal:

    _OCTAL_DIGITS = "01234567"

    def __init__(self, octal_as_str):
        for c in octal_as_str:
            if c not in self._OCTAL_DIGITS:
                raise ValueError('Invalid octal digit: ' + c)

        while octal_as_str.startswith('0'):
            octal_as_str = octal_as_str[1:]

        self.octal = int(octal_as_str)

    def to_decimal(self):
        decimal = 0
        i = 0
        remainder = 0
        n = self.octal

        while n != 0:
            rem = n % 10
            n /= 10
            decimal += rem*pow(8, i)
            i += 1

        return decimal

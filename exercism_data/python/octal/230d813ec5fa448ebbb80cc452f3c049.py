class Octal(object):
    def __init__(self, string):
        self.string = string
        for x in string:
            if x not in str(range(8)):
                raise ValueError("Invalid octal digit: {}".format(x))

    def to_decimal(self):
        return sum(int(digit) * 8 ** i for i, digit in enumerate(reversed(self.string)))

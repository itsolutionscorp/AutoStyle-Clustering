class Luhn:

    def __init__(self, number):
        self.number = number

    def addends(self):
        old_digits = digits(self.number)
        luhn_transform = lambda n: (2 * n - 9) if (n > 4) else (2 * n)
        return [(luhn_transform(n) if (i % 2 == 0) else n)
                for i, n in enumerate(old_digits, start=len(old_digits) % 2)]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(n):
        diff = (10 - Luhn(n * 10).checksum()) % 10
        return 10 * n + diff


def digits(n):
    """Return the digits of a non-negative integer as a list of ints."""
    return [int(d) for d in str(n)]

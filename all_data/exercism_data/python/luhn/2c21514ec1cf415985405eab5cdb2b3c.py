class Luhn:
    def __init__(self, s):
        self.s = str(s)

    @classmethod
    def create(cls, n):
        s = str(n)
        c = (10 - Luhn(s + '0').checksum()) % 10
        return 10 * n + c

    def addends(self):
        return list(self.doubled())

    def doubled(self):
        for i, n in enumerate(reversed(self.s)):
            n = int(n)

            if i % 2 == 1:
                n *= 2
                if n > 10:
                    n = n - 9

            yield n

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

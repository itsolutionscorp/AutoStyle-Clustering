class Luhn:
    @classmethod
    def create(self, code):
        for i in range(0, 9):
            padded = int(str(code) + str(i))
            if Luhn(padded).is_valid() is True:
                return padded

    def __init__(self, code):
        self.code = code

    def addends(self):
        return self.__nine_corrected()

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def __check_digit__(self):
        self.code[-1]

    def __doubled_digits(self):
        flipped = list(reversed(str(self.code)))
        doubled = [2 * int(d) if ((i + 1) % 2 == 0) else int(d)
                   for i, d in enumerate(flipped)]
        return doubled

    def __nine_corrected(self):
        return list(reversed(
            [n - 9 if n > 9 else n for n in self.__doubled_digits()]
        ))

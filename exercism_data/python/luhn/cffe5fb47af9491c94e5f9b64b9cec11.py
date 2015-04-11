def _digits(number):
    return [int(n) for n in str(number)]

class Luhn(object):
    def __init__(self, number):
        self.number = int(number)

    def digits(self):
        return _digits(self.number)

    def addends(self):
        digits = self.digits()
        digits[-2::-2] = [sum(_digits(n * 2)) for n in digits[-2::-2]]
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def fix_checksum(self):
        self.number -= self.number % 10
        self.number += (10 - self.checksum()) % 10
        return self

    @classmethod
    def create(cls, number):
        with_checksum = cls(number * 10).fix_checksum()
        return with_checksum.number

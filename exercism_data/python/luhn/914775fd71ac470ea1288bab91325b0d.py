def _digits(number):
    return [int(n) for n in str(number)]

class Luhn(object):
    def __init__(self, number):
        self.number = number

    def addends(self):
        digits = _digits(self.number)
        digits[-2::-2] = [sum(_digits(n * 2)) for n in digits[-2::-2]]
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @classmethod
    def create(cls, number):
        check_digit = (10 - cls(number * 10).checksum()) % 10
        return number * 10 + check_digit


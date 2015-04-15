from math import floor

class Luhn:
    def __init__(self, n):
        self.value = n
    def addends(self):
        def to_digits(n):
            if n == 0:
                return []
            else:
                return to_digits(floor(n / 10)) + [n % 10]
        digits = to_digits(self.value)
        for i in range(-2, -len(digits)-1, -2):
            digits[i] *= 2
            if digits[i] >= 10:
                digits[i] -= 9
        return digits
    def checksum(self):
        return sum(self.addends()) % 10
    def is_valid(self):
        return self.checksum() == 0
    @staticmethod
    def create(n):
        checksum = Luhn(n * 10).checksum()
        add = 10 - checksum if checksum != 0 else 0
        return n * 10 + add

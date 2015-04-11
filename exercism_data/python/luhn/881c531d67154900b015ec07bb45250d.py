class Luhn:
    def __init__(self, n):
        self.n = n

    def addends(self):
        digits = [int(d) for d in str(self.n)]
        def normalize_digit(digit):
            return digit if digit < 9 else digit - 9
        digits[-2::-2] = [normalize_digit(d * 2) for d in digits[-2::-2]]
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(n):
        luhn_num = Luhn(n * 10)
        check_digit = luhn_num.checksum() * 9 % 10
        return n * 10 + check_digit

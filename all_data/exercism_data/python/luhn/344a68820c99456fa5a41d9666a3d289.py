mangle = {
    0 : 0,
    1 : 2,
    2 : 4,
    3 : 6,
    4 : 8,
    5 : 1,
    6 : 3,
    7 : 5,
    8 : 7,
    9 : 9
}

class Luhn:
    def __init__(self, number):
        self.number = number

    def addends(self):
        digits = [ int(d) for d in str(self.number) ]
        pos = len(digits) - 2
        while pos >= 0:
            digits[pos] = mangle[digits[pos]]
            pos -= 2
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        number_with_zero_checksum = number * 10
        checksum = Luhn(number_with_zero_checksum).checksum()
        checksum_required_for_valid_luhn = (10 - checksum) % 10
        return number_with_zero_checksum + checksum_required_for_valid_luhn

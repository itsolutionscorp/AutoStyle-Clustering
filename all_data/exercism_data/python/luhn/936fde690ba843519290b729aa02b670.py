class Luhn(object):
    def __init__(self, num):
        self.num = num

    def addends(self):
        digits = [ int(d) for d in str(self.num) ]
        for i in range(len(digits) - 2, -1, -2):
            digits[i] *= 2
            if digits[i] > 9:
                digits[i] -= 9
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(num):
        num *= 10
        return num + Luhn(num).checksum() * 9 % 10

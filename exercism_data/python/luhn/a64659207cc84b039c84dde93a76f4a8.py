class Luhn(object):
    def __init__(self, num):
        self.num = num

    def digits_of(self):
        return [int(i) for i in str(self.num)]

    def addends(self):
        digits = self.digits_of()
        for x in range(len(digits) - 2, -1, -2):
            digits[x] = digits[x] * 2
            if digits[x] > 9:
                digits[x] = digits[x] - 9
        return digits

    def is_valid(self):
        return sum(self.addends()) % 10 == 0

    def checksum(self):
        return sum(self.addends()) % 10

    @staticmethod
    def create(num):
        diff = (10 - Luhn(num * 10).checksum()) % 10
        return 10 * num + diff

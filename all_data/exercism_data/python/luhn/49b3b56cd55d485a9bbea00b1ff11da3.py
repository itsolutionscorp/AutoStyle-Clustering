class Luhn(object):
    def __init__(self, number):
        self.number = number

    def addends(self):
        return map(lambda val: val if val < 10 else val - 9, (val * 2 if ind % 2 else val for ind, val in enumerate(Luhn.getdigits(self.number)[::-1])))

    def checksum(self):
        checksum = sum(self.addends())
        return checksum if checksum < 10 else checksum % 10

    def is_valid(self):
        return self.checksum() % 10 == 0

    @staticmethod
    def create(number):
        checksum = sum(Luhn(number * 10).addends())

        if checksum > 9:
            checksum = checksum % 10

        if checksum > 0:
            checksum = 10 - checksum

        return number * 10 + checksum

    @staticmethod
    def getdigits(num):
        return [num] if num < 10 else Luhn.getdigits(num / 10) + [num % 10]

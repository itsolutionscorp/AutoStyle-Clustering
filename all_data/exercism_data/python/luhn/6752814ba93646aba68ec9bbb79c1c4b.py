class Luhn:

    def __init__(self, number):
        self.number = number

    def addends(self):
        addend = [int(n) for n in str(self.number)]
        for i in range(len(addend) - 2, -1, -2):
            addend[i] *= 2
            if addend[i] > 10: addend[i] -= 9

        return addend

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return not self.checksum()

    @staticmethod
    def create(n):
        diff = (10 - Luhn(n * 10).checksum()) % 10
        return 10 * n + diff

class Luhn (object):

    def __init__(self, number):
        self.number = number

    def addends(self):
        digits = [int(d) for d in str(self.number)]
        evenNum = False
        #is the first number a Luhn number (even or odd)?
        if len(digits) % 2 == 0:
            evenNum = True
        for n, d in enumerate(digits):
            if evenNum:
                if (d > 4):
                    digits[n] = 2 * d - 9
                else:
                    digits[n] = 2 * d
            evenNum = not evenNum
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def create(n):
        y = n * 10
        diff = (10 - Luhn(y).checksum()) % 10
        return y + diff

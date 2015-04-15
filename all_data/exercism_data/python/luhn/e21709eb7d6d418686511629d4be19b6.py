class Luhn(object):
    def __init__(self, x):
        self._addends = []
        self.digits = [int(i) for i in str(x)]

    @staticmethod
    def process_digit(digit_with_index):
        if digit_with_index[1] % 2 == 0:
            res = digit_with_index[0] * 2
            if res >= 10:
                return res - 9
            else:
                return res
        else:
            return digit_with_index[0]

    @staticmethod
    def make_addends(digits):
        digits_with_indices = zip(digits, range(len(digits), 0, -1))
        return [Luhn.process_digit(dwi) for dwi in digits_with_indices]

    def addends(self):
        if not self._addends:
            self._addends = Luhn.make_addends(self.digits)
        return self._addends

    def checksum(self):
        return sum(self.addends()) % 10

    @staticmethod
    def create(x):
        n = x*10
        csum = Luhn(n).checksum()
        return n + ((10-csum) % 10)

    def is_valid(self):
        return self.checksum() == 0

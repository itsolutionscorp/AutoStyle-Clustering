class Luhn:
    def __init__(self, number):
        self.number = number
        self._addends = self._calc_addends(number)
        self._checksum = sum(self._addends) % 10

    def addends(self):
        return self._addends

    def checksum(self):
        return self._checksum

    def is_valid(self):
        return self._checksum == 0

    @staticmethod
    def _calc_addends(number):
        addends = []
        digits = [int(i) for i in str(number)]
        for index, d in enumerate(digits[::-1]):
            if index % 2 == 1:
                d *= 2
                if d > 10:
                    d -= 9
            addends.append(d)
        return addends[::-1]

    @classmethod
    def create(cls, pre_luhn):
        last_digit = 10 - sum(cls._calc_addends(pre_luhn * 10)) % 10
        return pre_luhn * 10 + last_digit % 10




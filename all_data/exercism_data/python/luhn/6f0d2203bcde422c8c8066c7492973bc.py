class Luhn:

    def __init__(self, num):
        self._num = [int(x) for x in str(num)]

    def addends(self):
        return [Luhn.helper(x) for x in self._num[-2::-2]] + self._num[-1::-2]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def create(num):
        rem = Luhn(num*10).checksum()
        if rem == 0:
            return num * 10
        return num * 10 + 10-rem

    def helper(num):
        if num*2 > 9:
            return num*2 - 9
        return num*2

class Luhn(object):
    def __init__(self, n):
        self.n = [int(x) for x in str(n)]

    def _doublenumber(self, n):
        n = n * 2
        while n > 10:
            n -= 9
        return n

    def addends(self):
        number = self.n[:]
        number[-2::-2] = [self._doublenumber(k) for k in number[-2::-2]]
        return number

    def checksum(self):
        return sum(self.addends()) % 10
    
    def is_valid(self):
        return self.checksum() == 0

    @classmethod
    def create(cls, number):
        l = cls(str(number) + '0')
        return int(str(number) + str(10 - l.checksum())[-1])

class Luhn(object):
    def __init__(self, n):
        self.num = [int(i) for i in str(n)]

    def addends(self):
        num = list(self.num)
        for i in xrange(len(num)-2, -1, -2):
            n = num[i]*2
            num[i] = n if n < 10 else n - 9
        return num

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def __int__(self):
        return reduce((lambda acc, val: 10*acc+val), self.num)

    def __eq__(self, other):
        return int(self) == int(other)

    @staticmethod
    def create(n):
        l = Luhn(10*n)
        k = l.checksum()
        if k != 0:
            l.num[-1] = 10-k
        return l

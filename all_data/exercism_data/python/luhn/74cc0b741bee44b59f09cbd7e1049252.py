class Luhn:
    def __init__(self, n):
        self.n = n
        self.adds = []
        count = 1
        while n > 0:
            if count % 2 == 0:
                x = (n % 10) * 2
                if x > 9:
                    x -= 9
                self.adds.insert(0, x)
            else:
                self.adds.insert(0, n % 10)
            n /= 10
            count += 1

    def addends(self):
        return self.adds

    def checksum(self):
        return sum(self.adds)

    def is_valid(self):
        return self.checksum() % 10 == 0

    @staticmethod
    def create(n):
        for i in xrange(10):
            n2 = n * 10 + i
            if Luhn(n2).is_valid():
                return n2
        return 0

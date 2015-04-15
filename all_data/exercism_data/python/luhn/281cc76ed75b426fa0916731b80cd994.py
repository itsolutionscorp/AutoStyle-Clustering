class Luhn:
    def __init__(self, n):
        self.n = n

    def _convert(self, d, i):
        return  d if i % 2 else (2*d + (d>4)) % 10
    
    def addends(self):
        s = str(self.n)
        return [self._convert(int(s[i]), len(s)-i) for i in range(len(s))]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @classmethod
    def create(cls, m):
        return 10*m + -Luhn(10*m).checksum() % 10

    

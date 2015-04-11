class Luhn:
    def __init__(self, n):
        self.n = n
    
    @classmethod
    def create(cls, n):
        return n*10 + ((10 - cls(n*10).checksum()) % 10)

    @staticmethod
    def addend(i, v):
        if i % 2:
            return v * 2 if v < 5 else v * 2 - 9
        else:
            return v

    def addends(self):
        return (Luhn.addend(i, int(c)) for i, c in enumerate(reversed(str(self.n))))

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

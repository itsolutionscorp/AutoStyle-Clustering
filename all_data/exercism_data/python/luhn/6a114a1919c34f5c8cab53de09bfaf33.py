class Luhn:
    def __init__(self, n):
        self.digit = self.digitList(n)

    def digitList(self, n):
        d = [int(x) for x in str(n)]
        for i in range(len(d) - 2, -1, -2):
            d[i] *= 2
            if d[i] > 9:
                d[i] -= 9
        return list(d)


    def addends(self):
        d = list(self.digit)
        return d

    def checksum(self):
        return sum(self.digit) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod

    def create(n):
        n *= 10
        if not Luhn(n).checksum() == 0:
            n += 10 - Luhn(n).checksum()
        return n

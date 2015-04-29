class Luhn(object):
    def __init__(self, num):
        self.num = num

    def addends(self):
        n = self.num
        result = []
        double = False
        while n:
            n, rem = divmod(n, 10)
            if double:
                rem = 2 * rem - 9 if rem > 4 else 2 * rem
            result.append(rem)
            double = not double
        return result[::-1]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return sum(self.addends()) % 10 == 0

    @staticmethod
    def create(num):
        num *= 10
        l = Luhn(num)
        if l.is_valid():
            return num
        return num + (10 - l.checksum())

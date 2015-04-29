class Luhn:
    def __init__(self, n):
        self.n = n

    def checksum(self):
        return sum(self.addends()) % 10

    def addends(self):
        digits = [int(d) for d in str(self.n)]
        addend_list = digits[:]

        addend_list[-2::-2] = [i*2 if i*2 < 10 else i*2-9 for i in digits[-2::-2]]
        return addend_list

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(n):
        digit = Luhn(n * 10).checksum()
        if digit == 0:
            return n * 10
        else:
            return int(str(n) + str(10 - digit))

class Luhn(object):

    def __init__(self, num):
        self.num = num

    def addends(self):
        int_list = map(int, str(self.num))
        transform = lambda n: (2 * n - 9) if (n > 4) else (2 * n)
        return [(transform(n) if (i % 2 == 0) else n) for i, n in enumerate(int_list, start=len(int_list) % 2)]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(new_num):
        return 10 * new_num + ((10 - Luhn(new_num * 10).checksum()) % 10)

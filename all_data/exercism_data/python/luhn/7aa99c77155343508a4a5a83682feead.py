class Luhn(object):

    def __init__(self, number):
        self.number = number
        self.num_list = [int(d) for d in str(number)]

    def addends(self):
        if len(self.num_list) % 2 == 0:
            return [((2 * x - 9) if x > 4 else 2 * x) if i % 2 == 0 else x for i, x in enumerate(self.num_list)]
        return [((2 * x - 9) if x > 4 else 2 * x) if i % 2 != 0 else x for i, x in enumerate(self.num_list)]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        luhn = Luhn(number*10)
        if luhn.is_valid():
            return int("".join([str(i) for i in luhn.num_list]))
        return int("".join([str(i) for i in luhn.num_list[:-1]]+[str(10 - luhn.checksum())]))

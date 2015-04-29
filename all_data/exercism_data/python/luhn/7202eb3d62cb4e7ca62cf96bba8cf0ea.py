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

    @classmethod
    def create(cls, number):
        number_appended = number * 10
        remainder = (10 - cls(number_appended).checksum()) % 10
        return number_appended + remainder

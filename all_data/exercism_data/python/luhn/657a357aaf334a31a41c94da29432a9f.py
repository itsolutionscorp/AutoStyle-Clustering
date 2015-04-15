__author__ = 'Hinek'

class Luhn(object):

    def __init__(self, number):
        self.number = number

    def addends(self):
        list = [int(x) for x in str(self.number)]
        result = []
        for index, x in enumerate(list):
            if (index + len(list)) % 2 == 0:
                result.append(x * 2 if x * 2 < 10 else x * 2 - 9)
            else:
                result.append(x)
        return result

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(stub):
        for i in xrange(10):
            curr = int(str(stub) + '0') + i
            if Luhn(curr).is_valid():
                return Luhn(curr).number

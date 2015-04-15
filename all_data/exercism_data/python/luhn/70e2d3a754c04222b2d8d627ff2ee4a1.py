class Luhn(object):
    def __init__(self,number):
        self.number = number

    #doubles every other digit, subtracts 9 from digit that exceeds 10
    def addends(self):
        result = [int(x) for x in str(self.number)]
        for i in range(len(result)):
            if (i + 1 - len(result)) % 2 != 0:
                result[i] *= 2
            if result[i] >= 10:
                result[i] -= 9
        return result
                
    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() % 10 == 0

    @classmethod
    def create(cls,incomplete):
        test = cls(incomplete * 10)
        test.number += (10 - test.checksum()) % 10
        return test.number


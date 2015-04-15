class Luhn(object):
    def __init__(self, number):
        self.number = number

    @classmethod
    def create(cls, number):
        # start with a checksum of 0, then correct it as necessary
        num = Luhn(number*10)
        # add the amount needed to reach the next multiple of 10
        return num.number + (10 - num.checksum()) % 10

    def _transform_digit(self, index, digit):
        return digit if index % 2 == 0 else digit * 2 if digit < 5 else digit * 2 - 9

    def addends(self):
        # process number right-to-left
        # if order is important, just wrap this with another reversed()
        return [self._transform_digit(i, int(d))
                for i,d in enumerate(reversed(str(self.number)))]

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

__author__ = 'jimblackler'


class Luhn(object):

    def __init__(self, number):
        self.number = number

    def addends(self):
        values = []
        number_str = str(self.number)
        for i in xrange(0, len(number_str)):
            digit = int(number_str[i])
            if (len(number_str) - i) % 2 == 0:
                values.append(digit * 2 - 9 if digit * 2 >= 10 else digit * 2)
            else:
                values.append(digit)
        return values


    def checksum(self):
        return sum(self.addends()) % 10


    def is_valid(self):
        return self.checksum() == 0


    @staticmethod
    def create(number):
        test_number = number * 10 + 9
        luhn = Luhn(test_number)
        return test_number - luhn.checksum()

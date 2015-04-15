__author__ = 'Momo'

class Luhn:

    def __init__(self, number = 0):
        assert isinstance(number, int)
        self.__number_str = str(number)
        pass

    def addends(self):
        z = zip(range(len(self.__number_str)), reversed(self.__number_str))
        it = reversed([(i%2+1)*int(s) for (i,s) in z])
        mapper = lambda x : x - 9 if x > 9 else x
        return list(map(mapper, it))

    def checksum(self):
        return sum(self.addends())%10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number: int):
        diff = (10 - Luhn(number * 10).checksum())%10
        return number*10 + diff

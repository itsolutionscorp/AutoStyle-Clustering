class Luhn():
    def __init__(self, number='0'):
        self._num = str(number)

    @staticmethod
    def create(number):
        c = Luhn(str(number) + '0')
        return c.getluhn() + c.checksum()* 9 % 10

    def getluhn(self):
        return int(self._num)

    def is_valid(self):
        return self.checksum() == 0

    def checksum(self):
        return sum(i for i in self.addends()) % 10

    def addends(self):
        result = []
        even = False
        for i in reversed(self._num):
            x = int(i)
            if even:
                x*=2
                if x > 9:
                    x-=9
            even = not even
            result.insert(0, x)
        return result

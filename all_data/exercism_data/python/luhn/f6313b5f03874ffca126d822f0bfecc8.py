
class Luhn:
    def __init__(self, number):
        self._number = number
        self._digits = [int(n) for n in str(number)]

    def addends(self):
        self._digits[-2::-2] = [2*value if value < 5 else 2*value - 9
                                for value in self._digits[-2::-2]]
        return self._digits

    def checksum(self):
        return sum(self.addends())%10

    def is_valid(self):
        return self._number == self.create(self._number//10)

    @classmethod
    def create(cls, number):
        number *= 10
        return number + (10 - Luhn(number).checksum())%10 #mod 10 in case checksum is 0
        

class Luhn:
    def __init__(self,number):
        self.number = map(int,str(number))
    def checksum(self):
        return sum(self.addends()) % 10
    def is_valid(self):
        return self.checksum() == 0
    def addends(self):
        ans = self.number[::]
        ans[-2::-2] = map(lambda n: n*2 if n*2<10 else n*2-9,ans[-2::-2])
        return ans
    @staticmethod
    def create(number):
        return number*10 + (10 - Luhn(str(number) + '0').checksum()) % 10

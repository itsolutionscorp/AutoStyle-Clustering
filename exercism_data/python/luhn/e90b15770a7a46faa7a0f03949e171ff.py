class Luhn:
    
    def __init__(self, num):
        self.num = num

    def addends(self):
        digits = []
        iseven = False
        remainder = self.num
        while remainder > 0:
            currdigit = remainder % 10
            if iseven:
                currdigit *= 2
                if currdigit >= 10:
                    currdigit -= 9
                iseven = False
            else:
                iseven = True
            digits.append(currdigit)
            remainder /= 10
        return digits          

    def checksum(self):
        digits = self.addends()
        return sum(digits) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(num):
        testluhn = Luhn(10*num)
        checksum = testluhn.checksum()
        if checksum != 0:
            testluhn.num += (10 - checksum)
        return testluhn.num

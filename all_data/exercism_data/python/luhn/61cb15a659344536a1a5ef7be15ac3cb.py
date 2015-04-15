class Luhn:
    def __init__(self,num):
        self.num = num
        self.digits = list(map(int,str(self.num)))
    def addends(self):
        for a in range(len(self.digits)):
            if a % 2 == len(self.digits)%2:
                self.digits[a] *= 2
            if self.digits[a] > 9:
                self.digits[a] = (self.digits[a]%10)+1
        return self.digits
    def checksum(self):
        return sum(self.addends())%10
    def is_valid(self):
        if self.checksum() == 0:
            return True
        return False
    def create(number):
        for b in range(10):
            if Luhn((number*10)+b).is_valid():
                return (number*10)+b

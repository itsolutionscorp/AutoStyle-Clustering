class Luhn():

    def __init__(self, number):
        self.number = number
        
    @staticmethod
    def create(num):
        l = Luhn(num * 10)

        x = sum(l.addends()) % 10
        checkdigit = x if x == 0 else 10 - x

        return int(str(num) + str(checkdigit))

    def addends(self):
        total = []
        for i, v in enumerate(reversed(str(self.number))):
            if i % 2 == 0:
                value = int(v)
            else:
                value = int(v) * 2 if int(v) * 2 < 10 else int(v) * 2 - 9
            total = [value] + total
        return total
        
    def checksum(self):
        return sum(self.addends()) % 10
        
    def is_valid(self):
        return self.checksum() == 0

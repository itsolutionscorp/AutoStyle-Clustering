# William Morris
# exercism.io
# luhn.py

class Luhn():

    def __init__(self, number=0):
        self.number = number
        self.digits = []
        for x in range(len(str(number))-1,-1,-1):
            self.digits += [int(number // 10**x)]
            number %= 10**x
                
    def addends(self):
        test_digits = self.digits[:]
        odd_even = 0
        for i in range(len(test_digits)-1,-1,-1):
            if  odd_even%2:
                test_digits[i] *= 2
                if test_digits[i] >=10:
                    test_digits[i] -= 9
            odd_even += 1
        
        return test_digits
        
        
    def is_valid(self):
        return not sum(self.addends())%10

    def checksum(self):
        return sum(self.addends())%10

    @staticmethod
    def create(number):
        luhn = Luhn(number*10)
        checksum = luhn.checksum()
        if checksum:
            return number*10 + 10-checksum
        else:
            return number*10



class Luhn:
    def __init__(self, num):
        self.num = num
        
    def addends(self):
        n = self.num
        i = 1
        result = []
        while n > 0:
            digit = n % 10
            if i % 2 == 0:
                digit = digit * 2
                if digit >= 10:
                    digit -= 9
            result.insert(0, digit)
            n = n / 10
            i += 1
        return result        
        
    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(n):
        for i in xrange(0, 10):
            new_num = n * 10 + i
            obj = Luhn(new_num)
            if obj.is_valid():
                return new_num
        
if __name__ == '__main__':    
    print "You're running it wrong"

class Luhn(object):
    def __init__(self, number):
        a = [int(n) for n in str(number)]
        a = [a[n] if (len(a)+1)%2==n%2 else 2*a[n] for n in range(len(a))]
        self.addend = [o if o < 10 else o-9 for o in a]

    def addends(self):        
        return self.addend


    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(num):
        num *= 10
        l = Luhn(num)
        if l.is_valid():
            return num
        return num + (10 - l.checksum())

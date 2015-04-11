class Luhn:
    def __init__(self, n):
        self.o = self.createArray(n)
        self.n = self.createArray(n)

    def createArray(self, n):
        r = []
        while n > 0:
            r.insert(0, n%10)
            n /= 10
        return r

    def addends(self):
        self.n = self.o[:]
        for i in xrange(len(self.n)-2, -1, -2):
            t = self.n[i]
            t *= 2
            if t > 10:
                t -= 9
            self.n[i] = t
        return self.n 

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return not self.checksum()
        
    @classmethod
    def create(self, n):
        l = Luhn(n*10)
                
        if l.is_valid():
            return int(''.join(map(str, l.o)))

        z = l.checksum()
        z = 10 - z
        l.o[len(l.o)-1] = z
        return int(''.join(map(str, l.o)))



l = Luhn.create(873956)
print l
#l = Luhn(4913)
#print l.addends()
#print l.checksum()

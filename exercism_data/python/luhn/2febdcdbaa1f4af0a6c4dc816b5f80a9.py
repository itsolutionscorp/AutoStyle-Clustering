class Luhn():
    
    def __init__(self, intid):
        self.intid = intid
        self.id = map(int,[digit for digit in str(intid)])
        
    def addends(self):
        idlist = self.id[::-1]
        for i in xrange(len(idlist)):
            if not i % 2 == 0:
                idlist[i] *= 2
                if idlist[i] >= 10:
                    idlist[i] -= 9
        return idlist[::-1]
                    
    def checksum(self):
        return sum(self.addends()) % 10
        
    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(intid):
        id = intid * 10
        for i in range(10):
            luhn = Luhn(id+i)
            if luhn.is_valid():
                return luhn.intid

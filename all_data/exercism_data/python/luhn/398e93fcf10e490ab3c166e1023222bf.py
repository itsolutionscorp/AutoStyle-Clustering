class Luhn():
    
    def __init__(self, intid):
        self.intid = intid
        self.id = self.intToList(intid)
        
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
    def intToList(intid):
        id = []
        while intid > 0:
            id.insert(0,intid % 10)
            intid = intid // 10
        return id

    @staticmethod
    def create(intid):
        id = intid * 10
        for i in range(10):
            luhn = Luhn(id+i)
            if luhn.is_valid():
                return luhn.intid

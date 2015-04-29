class Luhn:
    def __init__(self, number=None):
        self.number = number
    def addends(self):
        count = 0
        l = [int(i) for i in str(self.number)]
        while count in range(0,len(l)):
            if count % 2:
                l[(-1*count)-1] *= 2
            count += 1
        l = list(map(lambda v: v-9 if v > 9 else v, l))
        return(l)
    def checksum(self):
        return(sum(self.addends()) % 10)
    def is_valid(self):
        return(self.checksum() == 0)
    def create(partial=None):
        p = Luhn(partial*10)
        if p.checksum():
            return(partial*10+(10-p.checksum()))
        else:
            return(partial*10)

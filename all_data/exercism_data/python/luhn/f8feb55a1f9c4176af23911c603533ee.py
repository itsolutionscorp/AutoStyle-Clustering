class Luhn:
    def __init__(self,num):
        self.num = num

    def addends(self):
        addends = [int(s) for s in str(self.num)[::-1]]
        for pos in range(1,len(addends),2):
            mult = addends[pos] * 2
            addends[pos] = mult if mult < 10 else mult - 9
        addends = addends[::-1]
        return addends

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def create(num):
        self = Luhn(num * 10)
        new_checksum =  - self.checksum() % 10
        return self.num + new_checksum

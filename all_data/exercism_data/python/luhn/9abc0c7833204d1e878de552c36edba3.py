class Luhn:
    def __init__(self,num):
        self.num = num

    def addends(self):
        addends = [int(s) for s in str(self.num)[::-1]]
        for pos in range(1,len(addends),2):
            mult = addends[pos] * 2
            if mult > 9:
                addends[pos] = mult - 9
            else:
                addends[pos] = mult
        addends = addends[::-1]
        return addends

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        if self.checksum() == 0:
            return True
        else:
            return False

    def create(num):
        self = Luhn(num * 10)
        new_checksum = (10 - self.checksum()) % 10
        return self.num + new_checksum

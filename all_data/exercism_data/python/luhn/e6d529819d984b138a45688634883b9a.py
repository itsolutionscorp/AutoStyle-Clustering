class Luhn:
    def __init__(self, num):
        self.num = num
    
    def addends(self):
        dig = [int(x) for x in str(self.num)]
        out = [None]*len(dig)
        count = 0
        for x in range(len(dig)-1,-1,-1):
            if count%2 != 0:
                if dig[x]*2 >= 10:
                    out[x] = dig[x]*2 - 9
                else:
                    out[x] = dig[x]*2
            else:
                out[x] = dig[x]
            count += 1
        
        return out
    
    def checksum(self):
        return sum(self.addends())%10
        
    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(num):
        if not Luhn(num).checksum():
            return num
        else:
            total = sum(Luhn(num*10).addends())
            if not total%10:
                return num*10
            else:
                out = num*10 + (10-total%10)
                return out

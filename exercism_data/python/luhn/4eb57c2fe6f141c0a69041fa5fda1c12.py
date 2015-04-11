class Luhn:
    def __init__(self,n):
        d = [int(c) for c in str(n)]
        d = [d[i] if i%2==(len(d)+1)%2 else 2*d[i] for i in range(len(d))]
        self.digits = [m if m < 10 else m-9 for m in d]
        
    def addends(self):
        return self.digits
    
    def checksum(self):
        return sum(self.addends()) % 10
        
    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(n):
        return 10*n + (10 - Luhn(10*n).checksum()) % 10

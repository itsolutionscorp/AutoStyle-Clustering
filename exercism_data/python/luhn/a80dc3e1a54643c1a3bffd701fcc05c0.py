class Luhn:
    def __init__(self,n):
        self.n = n
        
    def addends(self):
        n = self.n
        d = []
        while n:
            d.append(n % 10)
            n //= 10
        d = [d[i] if i%2==0 else 2*d[i] for i in range(len(d)-1,-1,-1)]
        return [m if m < 10 else m-9 for m in d]
    
    def checksum(self):
        return sum(self.addends()) % 10
        
    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(n):
        return 10*n + (10 - Luhn(10*n).checksum()) % 10

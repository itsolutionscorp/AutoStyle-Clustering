class Luhn:
    def __init__(self, n):
        self.value = n

    @staticmethod
    def create(n):
        return n*10 + ((10 - Luhn(n*10).checksum(n*10)) if Luhn(n*10).checksum(n*10) else 0)

    def checksum(self, n = None):
        if n: self.value = n
        return sum(self.addends()) % 10    

    def addends(self,):
        items = [int(value) for value in str(self.value)]
        for i in range(2,len(items)+1,2):
            items[-i] *= 2
            if items[-i] >= 10: items[-i] -= 9
        return items

    def is_valid(self,):
        return sum(self.addends()) % 10 == 0

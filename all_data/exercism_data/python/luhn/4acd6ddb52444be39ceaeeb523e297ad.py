def digits(n):
    while n:
        n, r = divmod(n, 10)
        yield(r)

class Luhn:
    def _addends_double(self, number):
        n = number * 2
        return n < 10 and n or n - 9

    def _addends_gen(self):
        return (i % 2 and self._addends_double(n) or n for i, n in enumerate(digits(self.number)))

    def __init__(self, number):
        self.number = number
        
    def addends(self):
        return list(self._addends_gen())
    
    def checksum(self):
        return sum(self._addends_gen()) % 10
    
    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(number):
        csum = (10 - Luhn(number * 10).checksum()) % 10
        return number * 10 + csum

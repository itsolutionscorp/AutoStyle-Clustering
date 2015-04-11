class Luhn(object):
    
    def __init__(self, number):
        self.number = number

    def addends(self):
        doubled = [int(i) for i in str(self.number)]
        size = len(doubled)
        curr = 0
        if size > 1:
            for d in range(size % 2, size, 2):
                curr = doubled[d] * 2
                if curr > 9: curr -= 9
                doubled[d] = curr  
        return doubled

    def checksum(self):
        return sum(self.addends()) % 10
    
    def is_valid(self):
        return self.checksum() == 0
        
    @staticmethod
    def create(number):
        return int(str(number) + 
                str((10 - Luhn(number * 10).checksum()) % 10))

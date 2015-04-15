class Luhn(object):
   
    def __init__(self,  number=0):
        self.number = number
    
    def addends(self):
        digits = [int(i) for i in str(self.number)]
        #create lambda function that will double or double-9 a number 'n'
        transform = lambda n : (2 * n - 9) if (n > 4) else (2 * n)
        #return num list from digits where every other n has been 'transformed'
        return [(transform(n) if (i % 2 == 0) else n)
                for i, n in enumerate(digits, start=len(digits) % 2)]              
        
    def checksum(self):
        return sum(self.addends()) % 10
        
    def is_valid(self):
        return self.checksum() == 0
            
    @staticmethod    
    def create(n):
        diff = (10 - Luhn(n * 10).checksum()) % 10
        return 10 * n + diff

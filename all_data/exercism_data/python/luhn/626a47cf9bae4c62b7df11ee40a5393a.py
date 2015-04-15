# Luhn

class Luhn(object):
    def __init__(self, num = None):
        self.num = num
        
    def addends(self):
        result = []   
        string_num = str(self.num)
        
        for i in range(-1, -len(string_num) - 1, -1):
            current_digit = int(string_num[i])
            if i % 2 == 0:
                current_digit *= 2
                if current_digit >= 10:
                    current_digit -=9
            result = [current_digit] + result
                
        return result
    
    def checksum(self):
        return sum(self.addends()) % 10 
            
    def is_valid(self):
        return sum(self.addends()) % 10 == 0
    
    @staticmethod
    def create(num):
        luhn = Luhn(num * 10)
        
        return (num * 10) + ((10 - luhn.checksum()) % 10)

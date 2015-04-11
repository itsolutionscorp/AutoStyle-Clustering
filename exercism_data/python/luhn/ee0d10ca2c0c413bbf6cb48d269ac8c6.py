translation = {x: (2*x) - 9*(2*x / 10) for x in xrange(10)}


def num_to_list(num):
    return map(int, str(num))
    
def list_to_num(lst):
    return int(''.join(map(str, lst)))
    
def addends(num):
    lst = num_to_list(num)
    
    for i in xrange(len(lst)-2, -1, -2):

        lst[i] = translation[lst[i]]
            
    return lst

class Luhn:
    
    def __init__(self, num):
        self.num = num
        
    @staticmethod
    def create(num):
        
        if not num:
            raise ValueError('Number must be non-zero') 
        
        while num / 1000 == 0: num *= 10
        
        def create(num):
            csum = sum(addends(num))
        
            if csum % 10 == 0:
                return num
        
            last_digit = num % 10
            dist_to_valid = 10 - csum % 10
        
            if dist_to_valid < (10 - last_digit):
                return num + dist_to_valid
            
            return create(num*10)
            
        return create(num)
        
    def addends(self):
        return addends(self.num)
        
    def is_valid(self):
        return self.checksum() == 0
        
    def checksum(self):
        return sum(self.addends()) % 10

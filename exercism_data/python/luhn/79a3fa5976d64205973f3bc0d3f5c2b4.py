class Luhn(object):
    
    def __init__(self,  number=0):
        self.number = list(str(number))
    
    def addends(self):
        new_number = []
        for i, e in list(enumerate(list(reversed(self.number)))):
            i_ = int(i)
            e_ = int(e)
            if i_ == 0:
                new_number.append(e_)
            elif i_%2 == 0:
                new_number.append(e_)
            else:
                if e_*2 >= 10:
                    new_number.append(e_*2-9)
                else:
                    new_number.append(e_*2)
        return list(reversed(new_number))
                
        
    def checksum(self):
        sum_ = reduce(lambda x, y: x+y, (self.addends()))
        return sum_%10
        
    def is_valid(self):
        if self.checksum() == 0:
            return True
        else:
            return False
            
    @staticmethod    
    def create(number):
        
        new_number = []
        for i, e in list(enumerate(list(reversed(list(str(number)))))):
            i_ = int(i)
            e_ = int(e)
            if i_ == 0:
                if e_*2 >= 10:
                    new_number.append(e_*2-9)
                else:
                    new_number.append(e_*2)
            elif i_%2 == 0:
                if e_*2 >= 10:
                    new_number.append(e_*2-9)
                else:
                    new_number.append(e_*2)
            else:
                new_number.append(e_)
        new_number= list(reversed(new_number))
        
        for i in range(0, 9):
            x = (reduce(lambda x, y: x+y, (new_number)) + i)%10
            if x == 0:
                create_number = i
                break
            else:
                pass
                
        return int(str(number) + str(create_number))

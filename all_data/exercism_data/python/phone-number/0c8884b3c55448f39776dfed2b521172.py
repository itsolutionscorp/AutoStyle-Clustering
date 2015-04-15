import re

class Phone(object):
    def __init__(self, number):
        # strip all non integers
        number = re.sub("[^0-9]","", number)
        digits = len(number)
        
        # number is bad if too small or too big
        if not 9 < digits < 12:
            self.number = "0000000000"
        # 10 digits is good
        elif digits == 10:
            self.number = number
        # 11 digits may be good...
        elif digits == 11:
            # if the leading digit is a 1
            if number[0] == '1':
                self.number = number[1:]
            else:
                self.number = "0000000000"      
    
    def area_code(self):
        return self.number[:3]
    
    def pretty(self):
        return "(" + self.area_code() + ") " + self.number[3:6] + '-' + self.number[6:]

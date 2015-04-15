import re

class Phone(object):

    def __init__(self, number):
        x = ''.join(re.findall("\d+", number))
        if len(x) != 10:
            if len(x) == 11 and x[0] == '1':
                self.number = x[1:]
            else:
                self.number = '0'*10
        elif len(x) == 10:
            self.number = x
    
    def area_code(self):
        return self.number[0:3]
        
    def pretty(self):
        return "({}) {}-{}".format(self.number[:3],
                                   self.number[3:6],
                                   self.number[6:])

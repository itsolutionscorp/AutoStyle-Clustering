from string import digits
class Phone(object):
    
    def __init__(self,number):
        
        self.number = self._valid_number(self.plain(number))
        
    def plain(self, number):
        return ''.join([num for num in list(number) if num in digits])
        
    def pretty(self):
        n = self.number
        area,prefix,line = slice(0,3), slice(3,6), slice(6,len(n))
        return '({0}) {1}-{2}'.format(n[area], n[prefix], n[line])
        
    def area_code(self):
        return self.number[0:3]
    
    def _valid_number(self,number):
        pnum,pnumN = number, len(number)
        
        if pnumN == 10:
            return pnum
        
        if  pnumN < 10 or pnumN > 11 or not pnum[0] == '1':
            return '0000000000'
        
        return pnum[1:]

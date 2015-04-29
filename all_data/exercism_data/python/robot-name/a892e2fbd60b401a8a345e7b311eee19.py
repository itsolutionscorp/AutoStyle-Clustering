from string import ascii_uppercase
from random import randrange as rr

class Robot(object):
    
    used_names = []
    alphaU = ascii_uppercase
    
    def __init__(self,digitsN=2,alphaN=3):
        self.digitsN = digitsN
        self.alphaN = alphaN
        self.name = self._generate_name()
        self.reset()
    
    def _unique_name(self):
        return not self.name in self.used_names
    
    def _generate_name(self):
        alpha  = [self.alphaU[rr(len(self.alphaU))] for x in xrange(self.digitsN)]
        digits = [str(rr(0,9)) for x in xrange(self.alphaN)]
        return ''.join(alpha+digits)
        
    def reset(self):
        while not self._unique_name():
            self.name = self._generate_name()
        self.used_names.append(self.name)

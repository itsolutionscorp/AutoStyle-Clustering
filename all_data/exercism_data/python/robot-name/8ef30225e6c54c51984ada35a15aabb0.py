from string import ascii_uppercase as alphaU, digits
from random import choice as rc

class Robot(object):
    
    used_names = []
    
    def __init__(self,digitsN=2,alphaN=3):
        self.digitsN = digitsN
        self.alphaN = alphaN
        self.name = self._generate_name()
        self.reset()
    
    def _unique_name(self):
        return not self.name in self.used_names
    
    def _generate_name(self):
        alpha  = [rc(alphaU) for x in xrange(self.digitsN)]
        num    = [str(rc(digits)) for x in xrange(self.alphaN)]
        return ''.join(alpha+num)
        
    def reset(self):
        while not self._unique_name():
            self.name = self._generate_name()
        self.used_names.append(self.name)

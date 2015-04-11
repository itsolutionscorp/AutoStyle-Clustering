
import random
from string import ascii_uppercase, digits

class Robot:
    @property
    def name(self):
        if not hasattr(self, '_name'):
            self._name = ''.join(map(random.choice, [ascii_uppercase]*2 + [digits]*3))
            
        return self._name

    def reset(self):
        del self._name
        

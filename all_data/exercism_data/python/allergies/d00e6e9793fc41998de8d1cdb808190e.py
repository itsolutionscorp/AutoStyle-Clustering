from itertools import compress

ALLERGENES = ('eggs',
              'peanuts',
              'shellfish',
              'strawberries',
              'tomatoes',
              'chocolate',
              'pollen',
              'cats',
              )

class Allergies:
    
    def __init__(self, score):
        selectors = (score & 2**i > 0 for i in range(0, len(ALLERGENES)))
        self._allergenes = list(compress(ALLERGENES, selectors))
        
    def is_allergic_to(self, allergene):
        return self._allergenes.count(allergene) > 0
        
    @property
    def list(self):
        """List of allergies based on the person's allergie score."""
        return self._allergenes
            

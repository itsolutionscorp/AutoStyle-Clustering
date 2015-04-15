from collections import OrderedDict


_allergytable = OrderedDict([
    ("eggs", 1), 
    ("peanuts", 2), 
    ("shellfish", 4),
    ("strawberries", 8), 
    ("tomatoes", 16),
    ("chocolate", 32), 
    ("pollen", 64), 
    ("cats", 128)])


def _find_allergies(score):
    return [a for a in _allergytable.keys() if _allergytable[a] & score] 
        
   
class Allergies(object):
    def __init__(self, score):
        self._allergies = _find_allergies(score)

    @property
    def list(self): 
        return self._allergies[:]

    def is_allergic_to(self, allergen):
        return allergen in self._allergies

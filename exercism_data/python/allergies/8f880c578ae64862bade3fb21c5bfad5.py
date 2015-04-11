ALLERGENS = {}
_last_allergen = None

for token in '''* eggs (1)
* peanuts (2)
* shellfish (4)
* strawberries (8)
* tomatoes (16)
* chocolate (32)
* pollen (64)
* cats (128)'''.split():
  if token.isalpha():
    _last_allergen = token
  if token.startswith('('):
    ALLERGENS[int(token[1:-1])] = _last_allergen
    
class Allergies(object):
  
  def __init__(self, score):
    # Given the spec, it's not clear to me why 257 should be a valid score, and why it should mean 'eggs'. But okay, sure, whatever.
    if score >= 256:
      score -= 256
    
    self.list = [] # I take no responsibility for this stupid attribute name
    for exp in range(7, -1, -1):
      n = 2**exp
      if score >= n:
        self.list.append(ALLERGENS[n])
        score -= n
        
    # Oh, haha, the unit tests insist that the list is in ascending order by index for some unforeseaable reason. great.
    self.list.reverse()
      
    
  def is_allergic_to(self, allergen):
    return allergen in self.list

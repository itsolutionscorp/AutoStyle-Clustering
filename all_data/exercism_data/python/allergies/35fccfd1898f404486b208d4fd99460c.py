
class Allergies:

  _allergens = { '1'  : 'eggs',
                 '2'  : 'peanuts', 
                 '4'  : 'shellfish', 
                 '8'  : 'strawberries', 
                 '16' : 'tomatoes',
                 '32' : 'chocolate', 
                 '64' : 'pollen',
                 '128': 'cats'}

  def __init__(self, allergy_score):
    self.score = allergy_score
    self.list  = self._score_to_list(allergy_score)

  def __repr__(self):
    return 'Allergies({0.score!r})'.format(self)

  def __str__(self):
    return '({0.score!s})'.format(self)
 
  def is_allergic_to(self,allergen):
  	return allergen in self.list
  
  def _score_breakdown(self, allergy_score):
    """ breakdown allergy score into sum of powers of 2 """
    bits = bin(allergy_score)[:1:-1]
    breakdown = [str(2 ** i) for i in range(0,len(bits)) if bits[i]=='1']
    return breakdown

  def _score_to_list(self, allergy_score):
  	""" convert list of scores into possible allergens """
  	list_of_allergens = [ self._allergens[score]
  	                      for score in self._score_breakdown(allergy_score) 
  	                      if score in self._allergens ]
  	return list_of_allergens






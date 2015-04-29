class Allergies(object):
  allergens = ('eggs peanuts shellfish strawberries '
               'tomatoes chocolate pollen cats').split()

  def __init__(self, score):
    self._score = score
    self.list = []
    for num, allergen in enumerate(self.allergens):
      if self._score & (1 << num) != 0:
        self.list.append(allergen)

  def is_allergic_to(self, allergen):
    return allergen in self.list

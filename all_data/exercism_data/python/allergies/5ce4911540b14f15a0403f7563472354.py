class Allergies:

  allergens = {
    1: 'eggs',
    2: 'peanuts',
    4: 'shellfish',
    8: 'strawberries',
    16: 'tomatoes',
    32: 'chocolate',
    64: 'pollen',
    128: 'cats'
  }

  def __init__(self, score):
    self.score = score
    self.list = []

    for allergen in sorted(self.allergens):
      if self.score & allergen != 0:
        self.list.append(self.allergens[allergen])

  def is_allergic_to(self,allergen):
    return allergen in self.list

class Allergies:
  def __init__(self, score):
    allAllergens = ['eggs','peanuts','shellfish','strawberries','tomatoes',
                    'chocolate','pollen','cats']
    self.list = []
    for i in range(len(allAllergens)):
      if 2**i & score:
        self.list.append(allAllergens[i])

  def is_allergic_to(self, allergen):
    return allergen in self.list

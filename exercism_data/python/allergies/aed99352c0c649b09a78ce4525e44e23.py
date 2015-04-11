ALLERGENS = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

class Allergies(object):

  def __init__(self, score):
  # bit of a hack - we use :b to get the binary representation
  # and return the indexes where this is set to 1
    self.list = [allergen for (allergen, matches) in zip(ALLERGENS, reversed('{0:b}'.format(score))) if int(matches)]

  def is_allergic_to(self, allergen):
    return allergen in self.list

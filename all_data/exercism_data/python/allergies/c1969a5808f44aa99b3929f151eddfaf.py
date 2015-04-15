class Allergies:
  allergies = ['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']
  list = []

  def __init__(self, allergy_int):
    self.allergies_bin = list('{0:08b}'.format(allergy_int%256))
    self.list = [x for x in self.allergies if int(self.allergies_bin[self.allergies.index(x)])]
    self.list.reverse()
    

  def is_allergic_to(self, allergy):
    if allergy in self.list:
      return True
    else:
      return False

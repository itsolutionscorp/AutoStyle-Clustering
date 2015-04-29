class Allergies:
  allergies = ['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']
  list = []

  def __init__(self, allergy_int):
    self.allergies_bin = list('{0:08b}'.format(allergy_int%256)) #convert int to string formatted as byte '01110010'
    self.list = [x for x in self.allergies if int(self.allergies_bin[self.allergies.index(x)])] #filter list of all allergies according to their allergy score
    self.list.reverse() #reverse the list back as binary is backwards
    

  def is_allergic_to(self, allergy):
    if allergy in self.list:
      return True
    else:
      return False

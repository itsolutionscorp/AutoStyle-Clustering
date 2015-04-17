from math import log, floor

allergies_map = {
  1: 'eggs',
  2: 'peanuts',
  4: 'shellfish',
  8: 'strawberries',
  16: 'tomatoes',
  32: 'chocolate',
  64: 'pollen',
  128: 'cats'
}

allergies_inv_map = {
  'eggs': 1,
  'peanuts': 2,
  'shellfish': 4,
  'strawberries': 8,
  'tomatoes': 16,
  'chocolate': 32,
  'pollen': 64,
  'cats': 128
}

class Allergies:

  def __init__(self, score):
    allergies = []
    rem = score
    while rem != 0:
      allergy_num = int(2 ** floor(log(rem, 2)))
      rem -= allergy_num
      if allergy_num in allergies_map:
        allergies.append(allergies_map[allergy_num])
    self.list = allergies[::-1]

  def is_allergic_to(self, allergy):
    return allergy in self.list
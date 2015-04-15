from collections import OrderedDict
from itertools import combinations

MAPPING = OrderedDict([("eggs", 1), ("peanuts", 2), ("shellfish", 4), ("strawberries", 8), ("tomatoes", 16), ("chocolate", 32), ("pollen", 64), ("cats", 128)])

def _getAllergensForScore(score):
  for i in range(1, len(MAPPING)+1):
    cmbns = combinations(MAPPING.iterkeys(), i)
    for eachTuple in cmbns:
      if score == sum([MAPPING[allergen] for allergen in eachTuple]):
        return list(eachTuple)
  return []

class Allergies:
  def __init__(self, score):
    SENTINEL = sum([y for x, y in MAPPING.iteritems()]) + 1
    if score > SENTINEL:
      score -= int(score/SENTINEL)*(SENTINEL);
    self.list = _getAllergensForScore(score)

  def is_allergic_to(self, allergen):
    return allergen in self.list

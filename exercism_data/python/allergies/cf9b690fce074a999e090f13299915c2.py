class Allergies:
  allergyD = {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries',\
              16:'tomatoes', 32:'chocolate', 64:'pollen', 128:'cats'}

  def codeToL(self, n):
    allergyL = []
    for i in xrange(8):
      if n & 2**i:
        allergyL.append(self.allergyD[2**i])
    return allergyL

  def is_allergic_to(self, allergy):
    return allergy in self.list

  def __init__(self, allergyCode):
    self.allergyCode = allergyCode
    self.list = self.codeToL(self.allergyCode)

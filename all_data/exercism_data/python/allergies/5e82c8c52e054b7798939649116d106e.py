class Allergies(object):
  """ return list of allergens """

  def __init__(self, score):
    self.bin = bin(score)
    self.allergyList = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    self.list = self.getList()

  def is_allergic_to(self, allergen):
    """ return True/False if allergic """
    return allergen in self.list

  def getList(self):
    reading = self.bin[2:][::-1]   # truncate and reverse 0b string
    pairs = zip(reading, self.allergyList)
    return [ pair[1] for pair in pairs if int(pair[0]) ]

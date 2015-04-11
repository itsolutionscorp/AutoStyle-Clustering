class Allergies(object):
  def __init__(self,score):
    self.score = score
    self.allergents = ["eggs","peanuts","shellfish","strawberries",
                       "tomatoes","chocolate","pollen","cats"]
    self.list = [a for i,a in enumerate(self.allergents) if self.is_allergic_to_index(i) ]

  def is_allergic_to(self,item):
    item_index = self.allergents.index(item)
    return self.is_allergic_to_index(item_index)

  def is_allergic_to_index(self,item_index):
    return 2**item_index & self.score != 0

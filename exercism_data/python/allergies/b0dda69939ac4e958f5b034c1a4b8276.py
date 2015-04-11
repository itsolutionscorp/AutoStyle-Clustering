class Allergies(object):

  def __init__(self, score):
    self.score = score % 256
    self.all_items = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]
    self.list = []
    self.binary = "".join(reversed("{0:b}".format(self.score)))
    for bit in range(len(self.binary)):
      if self.binary[bit] == '1':
        self.list.append(self.all_items[bit])


  def is_allergic_to(self, item):

    return item in self.list

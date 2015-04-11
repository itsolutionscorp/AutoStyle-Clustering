import re


class Phone(object):


  def __init__(self, number):

    self.number = re.sub("[^\d]+", "", number)
    if len(self.number) != 10:
      if len(self.number) == 11 and self.number[0] == '1':
        self.number = self.number[1:]
      else:
        self.number = "0000000000"


  def area_code(self):

    return self.number[:3]


  def pretty(self):

    return "".join(['(', self.number[:3], ') ', self.number[3:6], '-', self.number[6:]])

class Luhn(object):

  def __init__(self, code):
    self.original_code = [ int(item) for item in reversed(list(str(code))) ]
    self.code = [ self.original_code[i] * 2 if i % 2 != 0 and self.original_code[i] < 5 else self.original_code[i] * 2 - 9 if i % 2 != 0 else self.original_code[i] for i in range (len(self.original_code)) ][::-1]
    self.check = sum(self.code) % 10

  def addends(self):
    return self.code

  def checksum(self):
    return self.check

  def is_valid(self):
    return self.check == 0

  @staticmethod
  def create(num):
    return num * 10 + ((10 - Luhn(num * 10).checksum()) % 10)

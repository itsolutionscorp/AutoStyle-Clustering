import re
import string

class Bob:
  def hey(self, string):
    message = Message(string)
    if message.blank():
      return 'Fine. Be that way.'
    if message.shouting():
      return 'Woah, chill out!'
    if message.question():
      return 'Sure.'
    return "Whatever."

class Message:
  def __init__(self, string):
    self.string = string or ''

  def blank(self):
    return string.strip(self.string) == ''

  def shouting(self):
    string = re.sub('[^A-Za-z]', '', self.string)
    return re.search('^[A-Z]+$', string)

  def question(self):
    return re.search('\?$', self.string)

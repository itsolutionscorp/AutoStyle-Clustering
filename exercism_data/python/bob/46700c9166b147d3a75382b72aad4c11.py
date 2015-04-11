import string;
class Bob:

  def __init__(self):
    self.fine = 'Fine. Be that way.'
    self.woah = 'Woah, chill out!'
    self.sure = 'Sure.'
    self.what = 'Whatever.'

  def hey(self, msg):
    if (msg == None):
      return self.fine
    if (not msg.strip()):
      return self.fine
    if (msg.upper() == msg):
      return self.woah
    if (msg[-1] == '?'):
      return self.sure
    return self.what

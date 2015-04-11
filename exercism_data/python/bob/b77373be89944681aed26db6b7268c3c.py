class Bob:
  def hey(self, msg):
    if self.is_silent(msg):
      return 'Fine. Be that way!'
    elif self.is_shouted(msg):
      return 'Woah, chill out!'
    elif self.is_question(msg):
      return 'Sure.'
    else:
      return 'Whatever.'

  def is_question(self, msg):
    return msg[-1] == '?'

  def is_shouted(self, msg):
    return msg.upper() == msg

  def is_silent(self, msg):
    if msg == None:
      return True
    else:
      return msg.strip() == ""

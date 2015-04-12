class Bob():
  def hey(self, message):
    message = Message(message)
    if message.is_query():
      return 'Sure.'
    elif message.is_silent():
      return 'Fine. Be that way.'
    elif message.is_shout():
      return 'Woah, chill out!'
    else:
      return 'Whatever.'

class Message():
  def __init__(self, message):
    self.content = message
  
  def is_silent(self):
    return self.content == ''

  def is_shout(self):
    return self.content == self.content.upper()

  def is_query(self):
    return self.content[-1:] == '?'
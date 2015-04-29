class Bob:
  def hey(self, message):
    if message is None or len(message) == 0 or message.isspace():
      return 'Fine. Be that way.'
    elif message.isupper():
      return 'Woah, chill out!'
    elif message.endswith('?'):
      return 'Sure.'
    else:
      return "Whatever."

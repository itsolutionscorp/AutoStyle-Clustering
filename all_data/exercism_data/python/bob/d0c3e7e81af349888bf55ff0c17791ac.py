class Bob:
  """Bob"""

  def hey(self, message):
    if message.isupper():
      return 'Woah, chill out!'
    elif message.isspace() or len(message) == 0:
      return 'Fine. Be that way!'
    elif message[-1] == '?':
      return 'Sure.'
    return 'Whatever.'

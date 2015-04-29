class Bob:
  def __init__(self):
    pass

  def hey(self, message):
    if message is None:
      m = ''
    else:
      m = message.strip()

    if m == '':
      return 'Fine. Be that way!'
    elif m == m.upper():
      return 'Woah, chill out!'
    elif m[-1] == '?':
      return 'Sure.'
    else:
      return 'Whatever.'

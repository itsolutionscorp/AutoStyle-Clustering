class Bob:
  def hey(self, message):
    if message is None:
      m = ''
    else:
      m = message.strip()

    if m == '':
      return 'Fine. Be that way!'
    elif m.isupper():
      return 'Woah, chill out!'
    elif m.endswith('?'):
      return 'Sure.'
    else:
      return 'Whatever.'

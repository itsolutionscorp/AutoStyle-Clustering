class Bob:
  def hey(self, message):
    if not message or not message.strip():
      return 'Fine. Be that way!'
    message = message.decode('latin-1')
    if message.isupper():
      return 'Woah, chill out!'
    if message.endswith('?'):
      return 'Sure.'
    return 'Whatever.'

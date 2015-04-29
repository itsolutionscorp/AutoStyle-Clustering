def hey(message):
  if not message.strip():
      return 'Fine. Be that way!'
  elif message.isupper():
      return 'Whoa, chill out!'
  if message.endswith('?'):
      return 'Sure.'
  return 'Whatever.'

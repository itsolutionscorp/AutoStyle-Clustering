def hey(message):
  message = message.strip()
  if not message:
    return('Fine. Be that way!')
  elif (message.isupper()):
    # 'ME?' counts as yelling at Bob, instead of as a question
    return('Whoa, chill out!')
  elif message.endswith('?'):
    return('Sure.')
  else:
    return('Whatever.')

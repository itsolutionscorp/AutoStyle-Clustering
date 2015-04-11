def hey(saywhat):
  
  saywhat = saywhat.strip()
  
  if not saywhat:
    return 'Fine. Be that way!'

  if saywhat.isupper():
    return 'Whoa, chill out!'

  if saywhat.endswith('?'):
    return 'Sure.'

  else:
    return 'Whatever.'

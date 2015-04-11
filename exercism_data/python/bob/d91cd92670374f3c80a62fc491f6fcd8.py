def hey(input):
  if input.isupper():
    return 'Whoa, chill out!'

  if input.endswith('?'):
    return 'Sure.'

  if input.strip() == '':
    return 'Fine. Be that way!'

  return 'Whatever.'

    

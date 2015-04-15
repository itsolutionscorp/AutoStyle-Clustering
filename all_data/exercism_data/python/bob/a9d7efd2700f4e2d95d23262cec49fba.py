def hey(str):
  if str.strip() == '':
    return 'Fine. Be that way!'
  if str.isupper():
    return 'Whoa, chill out!'
  if str[-1:] == '?':
    return 'Sure.'
  return 'Whatever.'

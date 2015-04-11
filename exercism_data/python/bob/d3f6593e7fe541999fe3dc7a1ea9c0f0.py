def hey(str):
  if str.isupper():
    return u'Whoa, chill out!'
  elif str.endswith('?'):
    return u'Sure.'
  elif str.isspace() or str == '':
    return u'Fine. Be that way!'
  else:
    return u'Whatever.'

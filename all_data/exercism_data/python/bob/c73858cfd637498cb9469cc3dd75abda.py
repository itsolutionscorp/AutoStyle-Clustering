import string

def hey(what):
  content = what.strip()
  result = 'Fine. Be that way!'

  if (content):
    if (content.isupper()):
      result = 'Whoa, chill out!'
    elif (content.endswith('?')):
      result = 'Sure.'
    else:
      result = 'Whatever.'

  return result

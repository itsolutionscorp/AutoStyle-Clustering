#/usr/bin/python

def hey(what):
  answer = ''

  if what.strip() == '':
    answer = 'Fine. Be that way!'
  elif what.isupper():
    answer = 'Whoa, chill out!'
  elif what.endswith('?'):
    answer = 'Sure.'
  else:
    answer = 'Whatever.'

  return answer

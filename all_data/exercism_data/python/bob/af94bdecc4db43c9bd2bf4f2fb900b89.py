#
# Alex B. Solution to bob.py on 10.3.14
#



def hey(what):


  if what.isupper():
    response = 'Whoa, chill out!'

  elif what.endswith('?'):
    response = 'Sure.'

  elif not what.strip():
    response = 'Fine. Be that way!'

  else:
    response = 'Whatever.'

  return response

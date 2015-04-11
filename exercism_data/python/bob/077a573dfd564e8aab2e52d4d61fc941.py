def hey(what):

  what = what.rstrip()

  if what == '':
    return 'Fine. Be that way!'

  is_yell = what.isupper()
  is_question = what.endswith('?')

  if is_yell:
    reply = 'Whoa, chill out!'
  elif is_question:
    reply = 'Sure.'  
  else:
    reply = 'Whatever.'

  return reply

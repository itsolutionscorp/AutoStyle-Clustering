def hey(what):

  what = what.rstrip()

  if what == '':
    return 'Fine. Be that way!'

  is_yell = what.upper() == what and what.lower() != what
  is_question = what[-1] == '?'

  if is_yell:
    reply = 'Whoa, chill out!'
  elif is_question:
    reply = 'Sure.'  
  else:
    reply = 'Whatever.'

  return reply

def hey(question):
  if question is None or len(question.strip()) == 0:
    return 'Fine. Be that way!'
  elif question.isupper():
    return 'Whoa, chill out!'
  elif question.endswith('?'):
    return 'Sure.'
  else:
    return 'Whatever.'

def hey(sentence):
  if not sentence or sentence.isspace():
  	return 'Fine. Be that way!'
  if sentence.isupper():
  	return 'Whoa, chill out!'
  if sentence.endswith('?'):
  	return 'Sure.'
  return 'Whatever.'

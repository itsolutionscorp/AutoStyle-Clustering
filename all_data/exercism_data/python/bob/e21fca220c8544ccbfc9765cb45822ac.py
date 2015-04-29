def hey(sentance):
  responses = {
    '': 'Whatever.',
    'question': 'Sure.',
    'silence': 'Fine. Be that way!',
    'upper': 'Whoa, chill out!'}

  sentance = sentance.strip()
  state = ''

  if sentance.endswith('?'):
    state = 'question'
  if sentance.isupper():
    state = 'upper'
  if sentance == '':
    state = 'silence'

  return responses[state]

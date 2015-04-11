class Bob:
  def hey(self, sentence):
    if not sentence or sentence.strip() == '':
      return 'Fine. Be that way!'
    elif sentence.upper() == sentence:
      return 'Woah, chill out!'
    elif sentence.endswith('?'):
      return 'Sure.'
    else:
      return 'Whatever.'

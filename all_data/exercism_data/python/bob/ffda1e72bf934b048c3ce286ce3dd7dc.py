import re

class Bob:
  def hey(self, sentence):
    if sentence[-1:] in ['.', '?', '!']:
      sentence_without_specials = re.sub('[^A-Za-z0-9]+', '', sentence[:-1])
    else:
      sentence_without_specials = re.sub('[^A-Za-z0-9]+', '', sentence)

    if sentence.strip() == '':
      return 'Fine. Be that way!'

    if sentence.upper() == sentence and not sentence_without_specials.isdigit():
      return 'Woah, chill out!'

    if sentence[-1:] == '?':
      return 'Sure.'

    return 'Whatever.'

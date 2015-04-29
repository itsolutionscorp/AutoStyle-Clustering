class Bob:
  def hey(self, phrase):
    if is_silence(phrase):
      return 'Fine. Be that way!'
    if is_shout(phrase):
      return 'Woah, chill out!'
    if is_question(phrase):
      return 'Sure.'
    return 'Whatever.'

def is_question(phrase):
  return phrase.endswith('?')

def is_silence(phrase):
  return not phrase or not phrase.strip()

def is_shout(phrase):
  return phrase.isupper()

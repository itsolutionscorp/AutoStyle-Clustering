def hey(phrase):
  phrase = phrase.strip()
  if len(phrase) == 0:
    return 'Fine. Be that way!'
  if phrase.isupper():
    return 'Whoa, chill out!'
  if phrase[-1] == '?' :
    return 'Sure.'
  return 'Whatever.'

class Bob:
  def hey(self, phrase):
    if phrase:
      phrase = phrase.strip()
    if not phrase:
      return 'Fine. Be that way!'
    elif phrase.upper() == phrase:
      return 'Woah, chill out!'
    elif phrase[-1] == '?':
      return 'Sure.'
    else:
      return 'Whatever.'

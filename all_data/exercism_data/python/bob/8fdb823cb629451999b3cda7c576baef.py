class Bob:
  def hey(self, something):
    if something is None or len(something) == 0 or something.isspace():
      return 'Fine. Be that way.'
    elif something.isupper():
      return 'Woah, chill out!'
    elif something.endswith('?'):
      return 'Sure.'
    else:
      return "Whatever."

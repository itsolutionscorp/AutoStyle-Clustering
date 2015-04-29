class Bob:
  """Bob"""

  def hey(self, hey):
    if hey.isupper():
      return 'Woah, chill out!'
    elif hey.isspace() or len(hey) == 0:
      return 'Fine. Be that way!'
    elif hey[-1] == '?':
      return 'Sure.'
    return 'Whatever.'

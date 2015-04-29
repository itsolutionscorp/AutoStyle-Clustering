class Bob:
  def hey(self, s):
    # check if "nothing" was said
    if s == None or s.strip() == '':
      return 'Fine. Be that way.'

    # detect SHOUTING
    if s.isupper():
      return 'Woah, chill out!'

    # detect questions?
    if s.endswith('?'):
      return 'Sure.'

    # otherwise, well, whatever.
    return 'Whatever.'

class Bob:
  def hey(self, data):
    if not data or (type(data) is str and data.strip() == ''):
      return 'Fine. Be that way.'
    if data.upper() == data:
      return 'Woah, chill out!'
    if data[-1:] == '?':
      return 'Sure.'
    return 'Whatever.'

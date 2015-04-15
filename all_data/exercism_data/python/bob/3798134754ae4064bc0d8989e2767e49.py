class Bob:
  def hey(self, data):
    if self.is_silence(data):
      return 'Fine. Be that way.'

    if self.is_yelling(data):
      return 'Woah, chill out!'

    if self.is_question(data):
      return 'Sure.'

    return 'Whatever.'

  def is_silence(self, data):
    return not isinstance(data, str) or data.strip() == ''

  def is_yelling(self, data):
    return data.isupper()

  def is_question(self, data):
    return data.endswith('?')

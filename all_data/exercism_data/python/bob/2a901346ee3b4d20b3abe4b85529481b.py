class Bob:
  def __init__(self):
    self

  def hey(self, input):
    if input == None or input.strip() == '':
        return 'Fine. Be that way!'
    elif input.isupper():
      return 'Woah, chill out!'
    elif input.endswith('?'):
      return 'Sure.'
    else:
      return 'Whatever.'

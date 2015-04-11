class Bob:
  def hey(self, input):
    if input is None or input=='' or input.isspace():
      return 'Fine. Be that way!'
    if input.upper() == input and input.lower() != input:
      return 'Woah, chill out!'
    if input.endswith('?'):
      return 'Sure.'
    return 'Whatever.' 

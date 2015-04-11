class Bob:
  def hey(self, text):
    if text == None or text.strip() == '':
      return 'Fine. Be that way!'
    elif text.upper() == text:
      return 'Woah, chill out!'
    elif text[-1:] == '?':
      return 'Sure.'
    else:
      return "Whatever."

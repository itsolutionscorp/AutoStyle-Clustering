class Bob:
  def hey(self, text):
    if not text or text.isspace(): # not saying anything
      return 'Fine. Be that way!'
    elif text.isupper(): # all caps
      return 'Woah, chill out!'
    elif text[-1]=='?': # question
      return 'Sure.'
    else: # anything else
      return 'Whatever.'

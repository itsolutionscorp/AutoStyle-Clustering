class Bob:

  def hey(self,quote):
    if len(quote) and not quote.isspace():
      if quote.isupper():
        return 'Woah, chill out!'
      else:
        if quote[-1] == '?':
          return 'Sure.'
        else:
          return 'Whatever.'
    else:
      return 'Fine. Be that way!'

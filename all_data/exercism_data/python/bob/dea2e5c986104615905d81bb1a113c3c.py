#!/usr/bin/python

class Bob:

  def hey(self, input):

    if input.isupper():
      return "Woah, chill out!"

    elif input.endswith("?"):
      return 'Sure.'

    elif input.lstrip() == '':
      return 'Fine. Be that way!'

    else:
      return "Whatever."

    

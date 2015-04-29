import re

class Bob():
  def hey(self, message):
    response = 'Whatever.'

    silent = message.strip() == ''
    shout = message.isupper()
    question = message.endswith('?')
    if shout:
      response = 'Woah, chill out!'
    elif question:
      response = "Sure."
    elif silent:
      response = 'Fine. Be that way!'

    return response

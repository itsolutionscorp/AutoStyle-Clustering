#!/usr/bin/python

class Bob(object):
  def __init__(self):
    pass

  def hey(self, msg = None):
    msg = msg.strip()

    if not msg:
      return 'Fine. Be that way!'
    elif msg.isupper():
      return 'Woah, chill out!'
    elif msg.endswith('?'):
      return 'Sure.'
    else:
      return 'Whatever.'

# Questions:
#Bob answers 'Sure.' if you ask him a question.
# ends with ?

#He answers 'Woah, chill out!' if you yell at him.
# YELL = all letters are caps

#He says 'Fine. Be that way!' if you address him without actually saying anything.
# empty string or ' '

#He answers 'Whatever.' to anything else.

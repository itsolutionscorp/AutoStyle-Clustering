import re

IGNORING = re.compile("\s*$");
YELLING = re.compile("[^a-z]+$");
QUESTION = re.compile(".*\?$");
class Bob:
  def hey(self, message):
    if message == None or IGNORING.match( message ):
      return 'Fine. Be that way!'

    if YELLING.match( message ):
      return 'Woah, chill out!'
    elif QUESTION.match( message ):
      return 'Sure.'
    else:
      return 'Whatever.'

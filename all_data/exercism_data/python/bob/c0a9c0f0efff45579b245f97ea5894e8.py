import re

class Bob:
  def hey(self, statement):
    if statement is None:
      statement = ''
    else:
      statement = statement.strip()

    if re.compile('^[^a-z]+$').match(statement):
      return 'Woah, chill out!'
    elif statement.endswith('?'):
      return 'Sure.'
    elif re.compile(".+").match(statement):
      return 'Whatever.'
    else:
      return 'Fine. Be that way!'

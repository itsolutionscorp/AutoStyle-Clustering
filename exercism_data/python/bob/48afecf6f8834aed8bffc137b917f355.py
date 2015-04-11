import re

class Bob:
  def hey(self, statement):
    def isShouting(statement):
      return statement.upper() == statement and re.search('[A-Z]', statement)
    def isSilence(statement): return statement.strip() == ''
    def isQuestion(statement): return statement.endswith('?')

    if isSilence(statement):
      return 'Fine. Be that way!'
    elif isShouting(statement):
      return 'Woah, chill out!'
    elif isQuestion(statement):
      return "Sure."
    else:
      return "Whatever."

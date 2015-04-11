import re
class Bob:
  
  def hey(self, s):
    if re.match('^\s*$', s) or not len(s):
      return 'Fine. Be that way!'
    elif s == s.upper() and re.search('[A-Z]', s):
      return 'Woah, chill out!'
    elif s[-1] == '?':
      return 'Sure.'
    else:
      return 'Whatever.'

import re

class Bob(object):
  def hey(self, prompt):
    if prompt is None or len(prompt.strip()) == 0:
      return 'Fine. Be that way!'

    if re.search(r'[a-z]', prompt, re.I) and prompt.upper() == prompt:
      return 'Woah, chill out!'

    if prompt[-1] == '?':
      return 'Sure.'

    return 'Whatever.'

import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(rawWhat):
  what = rawWhat.strip()

  # If the stripped message is empty.
  if not what:
    return 'Fine. Be that way!'

  # If all alphabetic characters are uppercase, suggesting shouting.
  allChars = re.sub(r'[^A-z]', '', what)
  if len(allChars) > 0 and what == what.upper():
    return 'Whoa, chill out!'
 
  # If the last character is a question mark, suggesting a question.
  if what[-1] == '?':
    return 'Sure.'

  return 'Whatever.'

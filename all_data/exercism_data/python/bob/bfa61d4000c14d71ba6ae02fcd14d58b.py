#
# Skeleton file for the Python "Bob" exercise.
#
def hey(rawWhat):
  what = rawWhat.strip()

  # If the stripped message is empty.
  if not what:
    return 'Fine. Be that way!'

  # If all alphabetic characters are uppercase, suggesting shouting.
  if what.isupper():
    return 'Whoa, chill out!'
 
  # If the last character is a question mark, suggesting a question.
  if what.endswith('?'):
    return 'Sure.'

  return 'Whatever.'

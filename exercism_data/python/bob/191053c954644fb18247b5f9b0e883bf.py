#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):

  # If nothing was said
  if not what.strip():
    return 'Fine. Be that way!'

  # Get type of sentence
  qType = what[-1]

  # Process out numbers and unnecessary characters
  for word in what:
    what = re.sub('[\d\-\._?!,]*', '', what).strip()

  # Is the sentence all uppercase
  isAllUppercase = len([word for word in what if word.islower()]) < 1

  if isAllUppercase and len(what) > 1:
    return 'Whoa, chill out!'
  elif qType == '?':
    return 'Sure.'
  else:
    return 'Whatever.'

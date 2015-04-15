#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
  response = ""

  if re.search(r'^$|^[\s|\t]*$',what):
    response = "Fine. Be that way!"
  elif isYelling(what):
    response = "Whoa, chill out!"
  elif re.search(r'\?\s*$',what):
    response = "Sure."
  else:
    response = "Whatever."

  return response

def isYelling(str):
  if [c for c in str if c.islower()] or re.search(r'[A-Z]',str) == None:
    return False
  else:
    return True

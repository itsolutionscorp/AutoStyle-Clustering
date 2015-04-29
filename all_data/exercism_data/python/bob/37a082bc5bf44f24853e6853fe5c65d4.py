#
# Skeleton file for the Python "Bob" exercise.
#

import string as str

def hey(what):
  saynothing,phrase=True,False
  for ch in what:
    if (ch in str.ascii_letters):
      phrase=True
    if(ch not in str.whitespace):
      saynothing=False 
  if(saynothing==True):
    return 'Fine. Be that way!'
  elif(phrase == True and what == what.upper()):
    return 'Whoa, chill out!' 
  elif(what[::-1].lstrip()[0] == '?'):
    return 'Sure.'
  else:
    return 'Whatever.'

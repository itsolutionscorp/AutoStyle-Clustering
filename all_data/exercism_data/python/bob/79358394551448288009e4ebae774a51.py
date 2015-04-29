#
# Skeleton file for the Python "Bob" exercise.
#

"""
Bob answers 'Sure.' if you ask him a question.
He answers 'Whoa, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying
anything.
He answers 'Whatever.' to anything else.
"""

def hey(what):

  #get rid of whitespace
  what = what.strip()

  if what == '':
    return 'Fine. Be that way!'
  elif what.isupper():
    return 'Whoa, chill out!'
  elif what[-1] == '?':
    return 'Sure.'
  else:
    return 'Whatever.'

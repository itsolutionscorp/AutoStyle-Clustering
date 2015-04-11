# Dirk Herrmann's solution version 1 for exercism exercise "Bob"

# This file implements class Bob:
#
#    (The following documentation stems almost completely from the exercise
#    description found in README.md.)
#
# Bob is a lackadaisical teenager. In conversation, his responses are very
# limited.
# * Bob answers 'Woah, chill out!' if yelled at.
# * He answers 'Sure.' to any question (unless yelled).
# * He says 'Fine. Be that way!' when addressed without saying anything.
# * He answers 'Whatever.' to anything else.
#
# Examples can be found in the test suite in bob_test.py.

import re

# Case related helper functions

def hasUppercaseCharacters(string):
   return string.lower() != string

def hasLowercaseCharacters(string):
   return string.upper() != string

# Helper functions for phrase classification

def isSilence(phrase):
   # Check if phrase consists of whitespace only (if any)
   return re.match("^\\s*$", phrase)

def isYell(phrase):
   return hasUppercaseCharacters(phrase) and not hasLowercaseCharacters(phrase)

def isQuestion(phrase):
   return (len(phrase) > 0) and (phrase[-1] == "?")

# And here comes Bob himself:

class Bob:
   def hey(self, phrase):
      if isSilence(phrase):
          return "Fine. Be that way!"
      elif isYell(phrase):
          return "Woah, chill out!"
      elif isQuestion(phrase):
          return "Sure."
      else:
          return "Whatever."

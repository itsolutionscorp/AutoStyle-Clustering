# Dirk Herrmann's solution version 3 for exercism exercise "Bob"

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

# Helper functions for phrase classification

def isSilence(phrase):
   return not phrase.strip()

def isYell(phrase):
   return phrase.isupper()

def isQuestion(phrase):
   return phrase.rstrip().endswith("?")

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

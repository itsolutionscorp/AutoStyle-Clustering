# Dirk Herrmann's solution version 4 for exercism exercise "Bob"

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

def is_silence(phrase):
   return not phrase.strip()

def is_yell(phrase):
   return phrase.isupper()

def is_question(phrase):
   return phrase.endswith("?")

# And here comes Bob himself:

class Bob(object):
   def hey(self, phrase):
      if is_silence(phrase):
          return "Fine. Be that way!"
      elif is_yell(phrase):
          return "Woah, chill out!"
      elif is_question(phrase):
          return "Sure."
      else:
          return "Whatever."

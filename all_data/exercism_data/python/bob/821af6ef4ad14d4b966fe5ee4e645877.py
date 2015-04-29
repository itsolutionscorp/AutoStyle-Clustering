"""Bob is a lackadaisical teenager.

Bob answers 'Sure.' if you ask him a question.

He answers 'Woah, chill out!' if you yell at him (ALL CAPS).

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""

import string


class Bob(object):

  def hey(self, what):
    if what is None or not what.strip():
      return 'Fine. Be that way!'
    elif what.isupper():
      return 'Woah, chill out!'
    elif what.endswith('?'):
      return 'Sure.'
    else:
      return 'Whatever.'

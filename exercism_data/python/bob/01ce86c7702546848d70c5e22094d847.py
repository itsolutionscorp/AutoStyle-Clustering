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

    if not any(c in what
               for c in string.ascii_lowercase):
      return 'Woah, chill out!'

    if what.endswith('?'):
      return 'Sure.'

    return 'Whatever.'

#
# Bob.
#
import re


def hey(what):
    # Remove all leading and trailing spaces.
    what = what.strip()

    # Bob has a very limited responses.
    # He says 'Fine. Be that way!' if you address him
    # without actually saying anything.
    if what == '':
        return 'Fine. Be that way!'

    # He answers 'Whoa, chill out!' if you yell at him.
    # Yelling mean all [a-z] characters in the sentence are in UPPERCASE.
    # And there must be some [a-z] characters in it.
    if re.search('[a-zA-Z]', what) and what == what.upper():
        return 'Whoa, chill out!'

    # He answers 'Sure.' for any questions.
    if what.endswith('?'):
        return 'Sure.'

    # And he answers 'Whatever.' to anything else.
    return 'Whatever.'

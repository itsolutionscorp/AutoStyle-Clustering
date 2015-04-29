#
# Exercism 'bob.py' exercise. Better solution would involve NLP and AI, but ain't got no time (or expertise) for that!
#
# Thanks for reviewing!
import re
def hey(what):
    def _nagging(what):
        return re.match('^\s*$', what)

    def _yelling(what):
        return what.isupper() and re.search('[!?]{0,1}\s*$', what)

    def _asking(what):
        return re.search('\?\s*$', what)

    if _nagging(what):
        response = 'Fine. Be that way!'
    elif _yelling(what):
        response = 'Whoa, chill out!'
    elif _asking(what):
        response = 'Sure.'
    else:
        response = 'Whatever.'

    return response

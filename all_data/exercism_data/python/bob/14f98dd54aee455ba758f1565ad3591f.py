#
# Skeleton file for the Python "Bob" exercise.
#

import re
import unicodedata

def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'

    known_abbrv = set(['OK', 'DMV'])
    for abbr in known_abbrv:
        what = what.replace(abbr, '')

    what = remove_accents(what)
    words = re.findall('\w+', what)
    for word in words:
        if word.isupper():
            return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'


def remove_accents(input_str):
    nkfd_form = unicodedata.normalize('NFKD', input_str)
    return u"".join([c for c in nkfd_form if not unicodedata.combining(c)])

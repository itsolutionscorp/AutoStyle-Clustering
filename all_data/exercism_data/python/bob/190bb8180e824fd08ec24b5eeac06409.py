import re
import unicodedata
#
# Skeleton file for the Python "Bob" exercise.
#
def strip_accents(s):
    return ''.join(c for c in unicodedata.normalize('NFD', s) if unicodedata.category(c) != 'Mn')

def hey(what):
    what = strip_accents(what).strip()
    letters = re.sub('[^a-zA-Z]', '', what)
    if letters.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif re.match('^\s*$', what):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
    return

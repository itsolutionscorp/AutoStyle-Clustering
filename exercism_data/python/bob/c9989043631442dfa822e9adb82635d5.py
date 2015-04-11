import re
import unicodedata

def hey(what):
    # from http://www.peterbe.com/plog/unicode-to-ascii
    what = unicodedata.normalize('NFKD', what).encode('ascii','ignore')
    what = what.strip()

    if (len(what) == 0):
        return 'Fine. Be that way!'

    lowercase_pattern = re.compile(ur'[a-z]')
    uppercase_pattern = re.compile(ur'[A-Z]')
    # strings that don't have any lower case letters, and at least one upper
    if (not lowercase_pattern.search(what)
        and uppercase_pattern.search(what)):
        return 'Whoa, chill out!'

    if (what.strip()[-1] == '?'):
        return 'Sure.'

    return 'Whatever.'

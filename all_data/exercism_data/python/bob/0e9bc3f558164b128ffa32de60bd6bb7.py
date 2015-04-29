#
# Skeleton file for the Python "Bob" exercise.
#

import regex

def hey(what):
    if len(what) == 0 or len(what.strip()) == 0:
        return 'Fine. Be that way!'

    clean_what = what.strip()

    if (regex.search('[A-Z]', clean_what) and regex.sub('[A-Z]', '', clean_what) == regex.sub('[\p{alpha}]', '', clean_what)):
        return 'Whoa, chill out!'
        
    if (clean_what[-1] == '?'):
        return 'Sure.'
    return 'Whatever.'

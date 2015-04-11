#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'

    known_abbrv = set(['OK', 'DMV'])
    for abbr in known_abbrv:
        what = what.replace(abbr, '')

    if unicode(what).isupper():
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'

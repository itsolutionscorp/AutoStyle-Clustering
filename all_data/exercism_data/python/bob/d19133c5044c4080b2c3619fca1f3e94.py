#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what.endswith('?') and not what.isupper(): return 'Sure.'
    elif what.isupper(): return 'Whoa, chill out!'
    elif len(what)<=0: return 'Fine. Be that way!'
    else: return 'Whatever.'
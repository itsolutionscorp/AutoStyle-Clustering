#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip(' \t0123456789')
    if len(what) == 0:
        return 'Fine. Be that way!'
    elif what == what.upper() and what.upper() != what.lower():
        return 'Whoa, chill out!'
    elif what[-1]=='?':
        return 'Sure.'  
    else:
        return 'Whatever.'

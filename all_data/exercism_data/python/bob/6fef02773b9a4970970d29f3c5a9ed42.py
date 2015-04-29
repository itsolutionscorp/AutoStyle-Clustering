#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    if '?' in what and what.strip()[-1] == '?':
        return 'Sure.'    
    elif len(what.strip()) < 1:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

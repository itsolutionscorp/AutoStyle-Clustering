#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == '':
        return 'Fine. Be that way!'
    if is_yelling(what):
        return 'Whoa, chill out!'
    if what[-1] == '\t' and what[-2] == ' ':
        return 'Fine. Be that way!'
    if  '?' in what and what[-1] != '.':
        return 'Sure.'
    return 'Whatever.'

def is_yelling(what):
    what.replace(' ', '')
    what.replace('.', '')
    what.replace('!', '')
    what.replace('?', '')
    if what.isupper():
        return 1

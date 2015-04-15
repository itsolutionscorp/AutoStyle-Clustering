#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """
    Question => 'Sure.', include '?'
    Yell => 'Whoa, chill out!', alpha is upper
    Nothing => 'Fine. Be that way!', non printable
    Other => 'Whatever.'
    """
    what = what.strip()
    what_alpha = ''.join([i for i in what if i.isalpha()])
    what_other = ''.join([i for i in what if not i.isalnum()])
    if what_alpha.isupper():
        return 'Whoa, chill out!'
    elif what == '':
        return 'Fine. Be that way!'
    elif what_other.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
